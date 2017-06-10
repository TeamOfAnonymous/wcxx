package com.anonymous.web;

import com.anonymous.domain.Organization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/6/8.
 */
@Controller
@Api(value = "登录管理", description = "提供登录服务")
public class LoginLinkController {

    @GetMapping(value = "goLogin")
    @ApiOperation(value = "去到登录页面")
    public String goLoginHtml() {
        return "login";
    }

    @GetMapping(value = "goHeader")
    @ApiOperation(value = "去到页眉")
    public String goHeader() {
        return "header";
    }

    @GetMapping(value = "goWorkbench")
    @ApiOperation(value = "去到我的工作台")
    public String goWorkbench() {
        return "workbench";
    }



}
