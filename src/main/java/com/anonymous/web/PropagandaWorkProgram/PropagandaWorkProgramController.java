package com.anonymous.web.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsProduced.dto.PropagandaMaterialsProducedStatisticalDto;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PropagandaWorkProgramServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传工作方案 Controller
 */
@Controller
@RequestMapping("PropagandaWorkProgram")
@Api(value="宣传工作方案",description = "提供 宣传工作方案 的 增删改查 服务 ")
public class PropagandaWorkProgramController {

    @Autowired
    private PropagandaWorkProgramServiceInter pspService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加")
    @ResponseBody
    public PropagandaWorkProgram add(@RequestBody PropagandaWorkProgram pwp ) {
        return pspService.add(pwp ) ;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public boolean add(@RequestBody String[] pwpIds ) {
        return pspService.delete(pwpIds ) ;
    }
}
