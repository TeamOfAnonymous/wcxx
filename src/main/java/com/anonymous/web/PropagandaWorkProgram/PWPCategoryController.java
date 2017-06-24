package com.anonymous.web.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PropagandaWorkProgramServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传工作方案分类 Controller
 */
@Controller
@RequestMapping("PWPCategory")
@Api(value="宣传工作方案分类",description = "提供 宣传工作方案分类 的 增删改查 服务 ")
public class PWPCategoryController {

    @Autowired
    private PWPCategoryServiceInter pwpcService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加")
    @ResponseBody
    public PWPCategory add(@RequestBody PWPCategory pwpc) {
        return pwpcService.add(pwpc) ;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public boolean add(@RequestBody String[] ids ) {
        return pwpcService.delete(ids ) ;
    }

    @GetMapping(value = "findAll")
    @ApiOperation(value = "删除")
    @ResponseBody
    public List<PWPCategory> findAll( ) {
        return pwpcService.findAll() ;
    }
}
