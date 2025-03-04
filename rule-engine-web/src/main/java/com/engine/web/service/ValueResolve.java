package com.engine.web.service;

import com.engine.core.value.Value;
import com.engine.web.vo.common.DataCacheMap;
import com.engine.web.vo.rule.RuleCountInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/7/17
 * @since 1.0.0
 */
public interface ValueResolve {

    /**
     * 解析值，变为Value
     *
     * @param type      0元素，1变量，2固定值
     * @param valueType STRING,COLLECTION,BOOLEAN,NUMBER
     * @param value     type=0则为元素id，type=2则为具体的值
     * @param cacheMap  解析数据所用的缓存数据
     * @return value
     */
    Value getValue(Integer type, String valueType, String value, DataCacheMap cacheMap);

    /**
     * 获取规则/变量配置所需数据缓存
     *
     * @return CacheMap
     */
    DataCacheMap getCacheMap();

    /**
     * 解析值，变为Value
     *
     * @param type      0元素，1变量，2固定值
     * @param valueType STRING,COLLECTION,BOOLEAN,NUMBER
     * @param value     type=0则为元素id，type=2则为具体的值
     * @return value
     */
    Value getValue(Integer type, String valueType, String value);

    /**
     * 获取规则/变量配置所需数据缓存
     *
     * @return CacheMap
     */
    DataCacheMap getCacheMap(RuleCountInfo ruleCountInfo);
}
