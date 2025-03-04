/**
 * Copyright (c) 2020 dingqianwen (761945125@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.engine.web.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.engine.web.annotation.ReSubmitLock;
import com.engine.web.enums.ErrorCodeEnum;
import com.engine.web.interceptor.AuthInterceptor;
import com.engine.web.store.entity.RuleEngineUser;
import com.engine.web.vo.user.UserData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 丁乾文
 * @create 2019/8/13
 * @since 1.0.0
 */
@Component
@Aspect
@Slf4j
@Order(-9)
public class ReSubmitLockAspect {
    @Resource
    private RedissonClient redissonClient;
    private static final String RESUBMIT_LOCK_KEY_PRE = "boot_resubmit_lock_key_pre";

    @Around("@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, ReSubmitLock lock) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        long time = lock.timeOut();
        //锁前缀加当前登录用户加被执行的类名加方法名
        String className = joinPoint.getTarget().getClass().getName();
        UserData userData = AuthInterceptor.USER.get();
        //生成lock key
        StringBuilder builder = new StringBuilder();
        builder.append(RESUBMIT_LOCK_KEY_PRE)
                .append(StringPool.UNDERSCORE)
                .append(userData.getId())
                .append(StringPool.UNDERSCORE)
                .append(className)
                .append(StringPool.UNDERSCORE)
                .append(method.getName());
        if (lock.lockKeyArgs()) {
            builder.append(Stream.of(joinPoint.getArgs())
                    .filter(Objects::nonNull)
                    .map(Object::toString).collect(Collectors.joining(StringPool.COMMA)));
        }
        String lockKey = builder.toString();
        RLock rLock = redissonClient.getLock(lockKey);
        if (!rLock.tryLock(0L, time, TimeUnit.MILLISECONDS)) {
            log.warn("{}方法锁已经存在，请勿重复操作！", method.getName());
            throw new ValidationException(ErrorCodeEnum.BOOT10011038.getMsg());
        }
        try {
            log.info("{}方法加锁成功，Lock Key:{}", method.getName(), lockKey);
            return joinPoint.proceed();
        } finally {
            rLock.unlock();
            log.info("{}方法锁已经移除，Lock Key:{}", method.getName(), lockKey);
        }
    }
}
