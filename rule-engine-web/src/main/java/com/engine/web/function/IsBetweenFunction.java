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
package com.engine.web.function;

import cn.hutool.core.lang.Validator;
import com.engine.core.annotation.Executor;
import com.engine.core.annotation.Function;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/8/30
 * @since 1.0.0
 */
@Slf4j
@Function
public class IsBetweenFunction {

    /**
     * 函数主方法，如果不存在抛出异常
     *
     * @param params 参数绑定，函数入参
     * @return true/false
     */
    @Executor
    public Boolean executor(@Valid Params params) {
        return Validator.isBetween(params.getValue(), params.getMin(), params.getMax());
    }


    @Data
    public static class Params {
        @NotNull
        private Integer value;
        @NotNull
        private Integer min;
        @NotNull
        private Integer max;
    }

}
