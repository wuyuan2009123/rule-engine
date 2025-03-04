package com.engine.web.controller;

import com.engine.web.annotation.RoleAuth;
import com.engine.web.service.FunctionService;
import com.engine.web.vo.base.request.IdRequest;
import com.engine.web.vo.base.request.PageRequest;
import com.engine.web.vo.base.response.PageResult;
import com.engine.web.vo.base.response.PlainResult;
import com.engine.web.vo.function.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dingqianwen
 * @date 2020/8/24
 * @since 1.0.0
 */
@Api(tags = "函数控制器")
@RestController
@RequestMapping("ruleEngine/function")
public class FunctionController {

    @Resource
    private FunctionService functionService;

    /**
     * 函数列表
     *
     * @param pageRequest param
     * @return list
     */
    @PostMapping("list")
    @ApiOperation("函数列表")
    public PageResult<ListFunctionResponse> list(@RequestBody PageRequest<ListFunctionRequest> pageRequest) {
        return functionService.list(pageRequest);
    }

    /**
     * 查询函数详情
     *
     * @param idRequest 函数id
     * @return 函数信息
     */
    @PostMapping("get")
    @ApiOperation("查询函数详情")
    public PlainResult<GetFunctionResponse> get(@RequestBody @Valid IdRequest idRequest) {
        PlainResult<GetFunctionResponse> plainResult = new PlainResult<>();
        plainResult.setData(functionService.get(idRequest.getId()));
        return plainResult;
    }

    /**
     * 函数模拟测试
     *
     * @param runFunction 函数入参值
     * @return result
     */
    @PostMapping("run")
    @ApiOperation("函数模拟测试")
    public PlainResult<Object> run(@Valid @RequestBody RunFunction runFunction) {
        PlainResult<Object> plainResult = new PlainResult<>();
        plainResult.setData(functionService.run(runFunction));
        return plainResult;
    }

}
