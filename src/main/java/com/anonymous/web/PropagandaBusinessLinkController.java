package com.anonymous.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WangZK on 2017/6/10.
 */
@Controller
@Api(value = "宣传事务模块的链接管理")
@RequestMapping(value = "propagandaBusiness")
public class PropagandaBusinessLinkController {

    @GetMapping(value = "goOverview")
    @ApiOperation(value = "去到宣传事务概览")
    public String goOverview() {
        return "propagandaBusiness/overview";
    }

    @GetMapping(value = "goBusinessManagement")
    @ApiOperation(value = "去到宣传事务管理")
    public String goBusinessManagement() {
        return "propagandaBusiness/businessManagement";
    }

    @GetMapping(value = "propagandaInformation/goPropagandaInformationApply")
    @ApiOperation(value = "去到宣传信息发布申请")
    public String goPropagandaInformationApply() {
        return "propagandaBusiness/propagandaInformation/apply";
    }

    @GetMapping(value = "propagandaInformation/goPropagandaInformationQuery")
    @ApiOperation(value = "去到宣传信息发布查询")
    public String goPropagandaInformationQuery() {
        return "propagandaBusiness/propagandaInformation/query";
    }

    @GetMapping(value = "propagandaInformation/goPropagandaInformationCategoryManagement")
    @ApiOperation(value = "去到宣传信息类别管理")
    public String goPropagandaInformationCategoryManagement() {
        return "propagandaBusiness/propagandaInformation/categoryManagement";
    }

    @GetMapping(value = "propagandaInformation/goPropagandaInformationStatistics")
    @ApiOperation(value = "去到宣传信息发布统计")
    public String goPropagandaInformationStatistics() {
        return "propagandaBusiness/propagandaInformation/statistics";
    }

    @GetMapping(value = "propagandaMaterialsProduced/goPropagandaMaterialsProducedApply")
    @ApiOperation(value = "去到宣传品（资料）制作申请")
    public String goPropagandaMaterialsProducedApply() {
        return "propagandaBusiness/propagandaMaterialsProduced/apply";
    }

    @GetMapping(value = "propagandaMaterialsProduced/goPropagandaMaterialsProducedQuery")
    @ApiOperation(value = "去到宣传品（资料）制作查询")
    public String goPropagandaMaterialsProducedQuery() {
        return "propagandaBusiness/propagandaMaterialsProduced/query";
    }

    @GetMapping(value = "propagandaMaterialsProduced/goPropagandaMaterialsProducedStatistics")
    @ApiOperation(value = "去到宣传品（资料）制作统计")
    public String goPropagandaMaterialsProducedStatistics() {
        return "propagandaBusiness/propagandaMaterialsProduced/statistics";
    }

    @GetMapping(value = "propagandaMaterialsRecipients/goPropagandaMaterialsRecipientsApply")
    @ApiOperation(value = "去到宣传物资领用申请")
    public String goPropagandaMaterialsRecipientsApply() {
        return "propagandaBusiness/propagandaMaterialsRecipients/apply";
    }

    @GetMapping(value = "propagandaMaterialsRecipients/goPropagandaMaterialsRecipientsQuery")
    @ApiOperation(value = "去到宣传物资领用查询")
    public String goPropagandaMaterialsRecipientsQuery() {
        return "propagandaBusiness/propagandaMaterialsRecipients/query";
    }

    @GetMapping(value = "propagandaMaterialsRecipients/goPropagandaMaterialsRecipientsStatistics")
    @ApiOperation(value = "去到宣传物资领用统计")
    public String goPropagandaMaterialsRecipientsStatistics() {
        return "propagandaBusiness/propagandaMaterialsRecipients/statistics";
    }


}
