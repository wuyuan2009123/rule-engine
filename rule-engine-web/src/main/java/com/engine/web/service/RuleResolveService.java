package com.engine.web.service;

import com.engine.core.rule.Rule;
import com.engine.web.store.entity.RuleEngineRule;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/7/16
 * @since 1.0.0
 */
public interface RuleResolveService {


    /**
     * 根据规则code查询解析一个规则
     *
     * @param ruleCode 规则code
     * @return rule
     */
    Rule getRuleByCode(String ruleCode);

    /**
     * 根据规则id查询解析一个规则
     *
     * @param id 规则id
     * @return rule
     */
    Rule getRuleById(Integer id);

    Rule ruleProcess(RuleEngineRule ruleEngineRule);
}
