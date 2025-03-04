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


import com.engine.core.condition.Operator;
import com.engine.core.exception.ConditionException;
import com.engine.core.condition.Compare;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/4/6
 * @since 1.0.0
 */
public class StringCompare implements Compare {


    private StringCompare() {

    }

    private static StringCompare stringCompare = new StringCompare();

    public static StringCompare getInstance() {
        return stringCompare;
    }

    @Override
    public boolean compare(Object leftValue, Operator operator, Object rightValue) {
        if (leftValue == null || rightValue == null) {
            return false;
        }
        if (!(leftValue instanceof String) || !(rightValue instanceof String)) {
            throw new ConditionException("左值/右值必须是STRING");
        }
        String leftValueStr = (String) leftValue;
        String rightValueStr = (String) rightValue;
        switch (operator) {
            case EQ:
                return leftValue.equals(rightValue);
            case NE:
                return !leftValue.equals(rightValue);
            case CONTAIN:
                return leftValueStr.contains(rightValueStr);
            case STARTS_WITH:
                return leftValueStr.startsWith(rightValueStr);
            case ENDS_WITH:
                return leftValueStr.endsWith(rightValueStr);
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }

    public static void main(String[] args) {
        System.out.println(StringCompare.getInstance().compare("1", Operator.EQ, "2"));
    }
}
