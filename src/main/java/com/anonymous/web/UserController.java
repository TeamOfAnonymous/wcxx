package com.anonymous.web;

import com.anonymous.domain.User;
import com.anonymous.service.inter.UserServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Controller
@Api(value = "用户人员管理", description = "提供用户人员的 增删改查 服务")
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

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户人员信息")
    @ResponseBody
    public User editUser(@RequestBody User user) {
        return userService.edit(user);
    }

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取用户人员")
    @ResponseBody
    public User getUser(@RequestParam String id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "getUsersForPage", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取用户人员")
    @ResponseBody
    public Page<User> getUsersForPage(@RequestParam Integer currentPage,
                                      @RequestParam Integer size,
                                      @RequestParam String name,
                                      @RequestParam String sex,
                                      @RequestParam String organization,
                                      @RequestParam String post) {
        return userService.getUsersForPage(currentPage, size, name, sex, organization, post);
    }


}
