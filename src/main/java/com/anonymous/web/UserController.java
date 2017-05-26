package com.anonymous.web;

import com.anonymous.domain.Organization;
import com.anonymous.domain.User;
import com.anonymous.service.inter.UserServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Controller
@Api("用户人员管理")
public class UserController {

    @Autowired
    private UserServiceInter userService;

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户人员")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "getUsersByOrganization", method = RequestMethod.GET)
    @ApiOperation(value = "通过组织机构id获取会员")
    @ResponseBody
    public List<User> getUsersByOrganization(@RequestParam String organization_id) {
        return userService.findByOrganization(organization_id);
    }


}
