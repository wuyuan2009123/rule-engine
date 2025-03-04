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
package com.engine.core.condition.compare;


import com.engine.core.condition.Compare;
import com.engine.core.condition.Operator;
import com.engine.core.exception.ConditionException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/4/6
 * @since 1.0.0
 */
public class BooleanCompare implements Compare {

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    private BooleanCompare() {

    }

    private static BooleanCompare booleanCompare = new BooleanCompare();

    public static BooleanCompare getInstance() {
        return booleanCompare;
    }

    @Override
    public boolean compare(Object leftValue, Operator operator, Object rightValue) {
        if (leftValue == null || rightValue == null) {
            return false;
        }
        if (!(leftValue instanceof Boolean) || !(rightValue instanceof Boolean)) {
            throw new ConditionException("左值/右值必须是BOOLEAN");
        }
        switch (operator) {
            case EQ:
                return leftValue.equals(rightValue);
            case NE:
                return !leftValue.equals(rightValue);
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}
