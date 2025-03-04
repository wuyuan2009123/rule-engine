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
package com.engine.core.condition;

import cn.hutool.core.collection.CollUtil;
import com.engine.core.Configuration;
import com.engine.core.Input;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/7/16
 * @since 1.0.0
 */
@Data
@Slf4j
public class ConditionGroup {

    private Integer id;

    private Integer orderNo;

    private List<Condition> conditions = new ArrayList<>();

    /**
     * 添加一个条件
     *
     * @param condition 条件信息
     */
    public void addCondition(@NonNull Condition condition) {
        Objects.requireNonNull(condition);
        Condition.verify(condition);
        this.conditions.add(condition);
    }


    /**
     * 条件集运算，组内条件为&关系，都为true时返回true，否则返回false
     *
     * @return 返回true时，所有条件全部成立
     */
    public boolean compare(Input input, Configuration configuration) {
        if (CollUtil.isEmpty(this.conditions)) {
            log.info("条件为空，直接返回结果");
            return true;
        }
        // 条件运算
        for (Condition condition : conditions) {
            String conditionName = condition.getName();
            if (!condition.compare(input, configuration)) {
                log.info("{}条件不成立", conditionName);
                return false;
            }
            log.info("{}条件成立", conditionName);
        }
        return true;
    }
}
