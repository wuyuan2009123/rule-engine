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

import com.engine.core.Configuration;
import com.engine.core.Input;
import com.engine.core.exception.ConditionException;
import com.engine.core.value.DataType;
import com.engine.core.value.Value;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/3/2
 * @since 1.0.0
 */
@Slf4j
@Data
public class Condition {

    private Integer id;

    /**
     * 条件名称
     */
    private String name;
    /**
     * 条件执行顺序
     */
    private Integer orderNo;
    /**
     * 条件左值
     */
    private Value leftValue;

    /**
     * 运算符
     */
    private Operator operator;

    /**
     * 条件右值
     */
    private Value rightValue;

    public boolean compare(Input input, Configuration configuration) {
        log.debug("条件信息:{}", this);
        Compare compare = ConditionCompareFactory.getCompare(leftValue.getDataType());
        Object lValue = leftValue.getValue(input, configuration);
        Object rValue = rightValue.getValue(input, configuration);
        log.debug("开始对比条件：{},左值：{}，运算符：{}，右值：{}", name, lValue, operator, rValue);
        return compare.compare(lValue, operator, rValue);
    }

    public static void verify(Condition condition) {
        Value leftValue = condition.getLeftValue();
        Operator operator = condition.getOperator();
        Value rightValue = condition.getRightValue();
        String name = condition.getName();
        if (Objects.isNull(operator)) {
            throw new ConditionException("条件:{}运算符不能为null", name);
        }
        if (Objects.isNull(leftValue)) {
            throw new ConditionException("条件:{}左值不能为null", name);
        }
        if (Objects.isNull(rightValue)) {
            throw new ConditionException("条件:{}右值不能为null", name);
        }
        // 左边类型必须等于右边类型
        if (!leftValue.getDataType().equals(DataType.COLLECTION) && !leftValue.getDataType().equals(rightValue.getDataType())) {
            throw new ConditionException("条件:{}左值类型与右值类型不匹配", name);
        }
        // 类型需要与运算符匹配
        List<Operator> symbol = leftValue.getDataType().getSymbol();
        if (!symbol.contains(operator)) {
            throw new ConditionException("条件:{}条件值与运算符不匹配", name);
        }
    }
}
