package com.anonymous.web.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPPAffairCategoryServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传工作方案子项目分类 Controller
 */
@Controller
@RequestMapping("PWPPAffairCategory")
@Api(value="宣传工作方案子项目分类",description = "提供 宣传工作方案子项目分类 的 增删改查 服务 ")
public class PWPPAffairCategoryController {


    @Autowired
    private PWPPAffairCategoryServiceInter pwppaService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加")
    @ResponseBody
    public PWPPAffairCategory add(@RequestBody PWPPAffairCategory pwppa) {
        return pwppaService.add(pwppa) ;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public boolean add(@RequestBody String pwpId ) {
        return pwppaService.delete(pwpId ) ;
    }
}
