package com.engine.web.function;

import cn.hutool.core.collection.CollUtil;
import com.engine.core.annotation.Executor;
import com.engine.core.annotation.Function;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * 集合去除重复数据
 *
 * @author 丁乾文
 * @create 2020/11/18
 * @since 1.0.0
 */
@Slf4j
@Function
public class CollectionDeduplicationFunction {

    @Executor
    public List<?> executor(List<?> list) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

}
