package com.anonymous.web;

import com.anonymous.domain.PropagandaMaterialsRecipients;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import com.anonymous.utils.StatisticsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 */
@Controller
@Api(value = "宣传物资领用申请", description = "提供宣传物资领用申请的 增删改查 服务")
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

    @RequestMapping(value = "getPropagandaMaterialsRecipientsStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取宣传物资领用")
    @ResponseBody
    public List<StatisticsUtil> getPropagandaMaterialsRecipientsStatistics(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipientsByApplicationDate(startDate, endDate);
    }

}
