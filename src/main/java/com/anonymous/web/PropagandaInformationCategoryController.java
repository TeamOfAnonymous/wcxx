package com.anonymous.web;

import com.anonymous.domain.PropagandaInformation.CategoryTree;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangZK on 2017/6/1.
 */
@Controller
@Api(value = "宣传信息类别管理", description = "提供宣传信息类别的 增删改查 服务")
public class PropagandaInformationCategoryController {

    @Autowired
    private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

    @RequestMapping(value = "addPropagandaInformationCategory", method = RequestMethod.POST)
    @ApiOperation(value = "添加宣传信息类别")
    @ResponseBody
    public PropagandaInformationCategory addPropagandaInformationCategory(@RequestBody PropagandaInformationCategory propagandaInformationCategory) {
        return propagandaInformationCategoryService.add(propagandaInformationCategory);
    }

    @RequestMapping(value = "editPropagandaInformationCategory", method = RequestMethod.POST)
    @ApiOperation(value = "修改宣传信息类别")
    @ResponseBody
    public PropagandaInformationCategory editPropagandaInformationCategory(@RequestBody PropagandaInformationCategory propagandaInformationCategory) {
        return propagandaInformationCategoryService.edit(propagandaInformationCategory);
    }

    @RequestMapping(value = "deletedPropagandaInformationCategory", method = RequestMethod.GET)
    @ApiOperation(value = "删除宣传信息类别")
    @ResponseBody
    public boolean deletedPropagandaInformationCategory(@RequestParam String id) {
        return propagandaInformationCategoryService.delete(id);
    }

    @RequestMapping(value = "getPropagandaInformationCategories", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有最顶层的宣传信息类别")
    @ResponseBody
    public List<PropagandaInformationCategory> getPropagandaInformationCategories() {
        return propagandaInformationCategoryService.findByPidIsNull();
    }

    @RequestMapping(value = "getCategoriesOfTree", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部宣传信息类别")
    @ResponseBody
    public Object[] getCategoriesOfTree(String mainCategory, String medium) {
        return propagandaInformationCategoryService.getCategoriesOfTree(mainCategory, medium);
    }

    @RequestMapping(value = "getPropagandaInformationCategoriesByPid", method = RequestMethod.GET)
    @ApiOperation(value = "通过父节点获取宣传信息类别")
    @ResponseBody
    public List<PropagandaInformationCategory> getPropagandaInformationCategoriesByPid(@RequestParam String pid) {
        return propagandaInformationCategoryService.findByPid(pid);
    }

}
