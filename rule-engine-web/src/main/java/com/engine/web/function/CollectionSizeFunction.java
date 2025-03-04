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

import com.engine.core.annotation.Executor;
import com.engine.core.annotation.FailureStrategy;
import com.engine.core.annotation.Function;
import com.engine.core.annotation.Param;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * 函数说明：求集合大小
 * 函数参数：
 * <blockquote>
 * <pre>
 *    List<String>  list
 * </pre>
 * </blockquote>
 * 函数返回值：{@link Integer}
 *
 * @author dingqianwen
 * @date 2020/7/19
 * @since 1.0.0
 */
@Slf4j
@Function
public class CollectionSizeFunction {

    @Executor
    public Integer executor(@Param("list") List<String> list) {
        return list.size();
    }

    @FailureStrategy
    public Integer failureStrategy() {
        return 0;
    }
}
