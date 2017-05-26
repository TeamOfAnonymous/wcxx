package com.anonymous.web;

import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WangZK on 2017/5/26.
 */
@Controller
@Api("宣传物资领用")
public class PropagandaMaterialsRecipientsController {

    @Autowired
    private PropagandaMaterialsRecipientsServiceInter propagandaMaterialsRecipientsService;

    @RequestMapping(value = "addPropagandaMaterialsRecipients", method = RequestMethod.POST)
    @ApiOperation(value = "添加宣传物资领用")
    @ResponseBody
    public PropagandaMaterialsRecipients addPropagandaMaterialsRecipients(@RequestBody PropagandaMaterialsRecipients propagandaMaterialsRecipients) {
        return propagandaMaterialsRecipientsService.add(propagandaMaterialsRecipients);
    }

    @RequestMapping(value = "getPropagandaMaterialsRecipients", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取宣传物资领用")
    @ResponseBody
    public PropagandaMaterialsRecipients getPropagandaMaterialsRecipients(@RequestParam String id) {
        return propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipients(id);
    }
}
