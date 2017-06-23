package com.anonymous.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WangZK on 2017/6/15.
 */
@Controller
@Api(value = "系统管理的链接管理")
@RequestMapping(value = "systemManagement")
public class SystemManagementLinkController {

    @GetMapping(value = "goOrganizationList")
    @ApiOperation(value = "去到组织机构列表页面")
    public String goOrganizationList() {
        return "systemManagement/organizationList";
    }

    @GetMapping(value = "goUserList")
    @ApiOperation(value = "去到用户列表")
    public String goUserList() {
        return "systemManagement/userList";
    }

    @GetMapping(value = "goAddUser")
    @ApiOperation(value = "去到添加用户页面")
    public String goAddUser() {
        return "systemManagement/addUser";
    }

    @GetMapping(value = "goEditUser/{id}")
    @ApiOperation(value = "去到添加用户页面")
    public String goEditUser(ModelMap modelMap, @PathVariable String id) {
        modelMap.addAttribute("user_id", id);
        return "systemManagement/addUser";
    }

}
