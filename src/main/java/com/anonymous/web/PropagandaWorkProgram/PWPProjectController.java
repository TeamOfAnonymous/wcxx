package com.anonymous.web.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPProject;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPPAffairCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPProjectServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传工作方案子项目 Controller
 */
@Controller
@RequestMapping("PWPProject")
@Api(value="宣传工作方案子项目",description = "提供 宣传工作方案子项目 的 增删改查 服务 ")
public class PWPProjectController {

    @Autowired
    private PWPProjectServiceInter pwppService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加")
    @ResponseBody
    public PWPProject add(@RequestBody PWPProject pwpp) {
        return pwppService.add(pwpp) ;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public boolean add(@RequestBody String pwppId ) {
        return pwppService.delete(pwppId ) ;
    }
}
