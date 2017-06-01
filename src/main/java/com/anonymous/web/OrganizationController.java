package com.anonymous.web;

import com.anonymous.domain.Organization;
import com.anonymous.service.inter.OrganizationServiceInter;
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
@Api(value = "组织机构管理", description = "提供组织机构的 增删改查 服务")
public class OrganizationController {

    @Autowired
    private OrganizationServiceInter organizationService;

    @RequestMapping(value = "addOrganization", method = RequestMethod.POST)
    @ApiOperation(value = "添加组织机构")
    @ResponseBody
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.add(organization);
    }

    @RequestMapping(value = "getOrganizations", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有最顶层组织机构")
    @ResponseBody
    public List<Organization> getOrganizations() {
        return organizationService.find();
    }

    @RequestMapping(value = "getOrganizationsByPid", method = RequestMethod.GET)
    @ApiOperation(value = "通过父节点获取组织机构")
    @ResponseBody
    public List<Organization> getOrganizationsByPid(@RequestParam String pid) {
        return organizationService.findByPid(pid);
    }

    @RequestMapping(value = "editOrganization", method = RequestMethod.POST)
    @ApiOperation(value = "修改组织机构")
    @ResponseBody
    public Organization editOrganization(@RequestBody Organization organization) {
        return organizationService.edit(organization);
    }

    @RequestMapping(value = "deletedOrganization", method = RequestMethod.GET)
    @ApiOperation(value = "删除组织机构")
    @ResponseBody
    public boolean deletedOrganization(@RequestParam String id) {
        return organizationService.delete(id);
    }

}
