package com.anonymous.web;

import com.anonymous.domain.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huangMP on 2017/5/26.
 * decription :宣传品制作申请 Controller
 */
@Controller
@Api(value="宣传品（资料）制作申请",description = "提供 宣传品制作申请 的 增删改查 服务 ")
public class PropagandaMaterialsProducedController {

    @Autowired
    private PropagandaMaterialsProducedServiceInter propagandaMaterialsProducedService;

    @PostMapping(value = "addPropagandaMaterialsProduced")
    @ApiOperation(value = "添加申请到草稿箱")
    @ResponseBody
    public PropagandaMaterialsProduced add(@RequestBody PropagandaMaterialsProduced propagandaMaterialsProduced , @RequestParam String propagandaInformationId) {
        return propagandaMaterialsProducedService.add(propagandaMaterialsProduced , propagandaInformationId );
    }

    @PutMapping(value = "applyPropagandaMaterialsProduced")
    @ApiOperation(value = "提交申请")
    @ResponseBody
    public PropagandaMaterialsProduced apply(@RequestParam String id) {
        return propagandaMaterialsProducedService.apply(id);
    }

    @GetMapping(value = "getPropagandaMaterialsProduced")
    @ApiOperation(value = "通过id查找申请")
    @ResponseBody
    public PropagandaMaterialsProduced find(@RequestParam String id) {
        return propagandaMaterialsProducedService.findById(id);
    }
}
