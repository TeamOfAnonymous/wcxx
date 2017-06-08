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
public class LoginController {

    @GetMapping(value = "goLogin")
    @ApiOperation(value = "去到登录页面")
    public String goLoginHtml() {
        return "login";
    }

}
