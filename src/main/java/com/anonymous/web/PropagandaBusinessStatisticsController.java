package com.anonymous.web;

import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */
@Controller
@Api(value = "宣传事务统计", description = "提供宣传事务统计的服务")
public class PropagandaBusinessStatisticsController {

    @Autowired
    private PropagandaMaterialsRecipientsServiceInter propagandaMaterialsRecipientsService;
    @Autowired
    private PropagandaMaterialsProducedServiceInter propagandaMaterialsProducedService;
    @Autowired
    private PropagandaInformationServiceInter PropagandaInformationService;



    @RequestMapping(value = "getPropagandaMaterialsRecipientsStatisticsForm", method = RequestMethod.GET)
    @ApiOperation(value = "宣传物资领用统计报表")
    @ResponseBody
    public List<Map<String,Integer>> getPropagandaMaterialsRecipientsStatisticsForm(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipientsStatisticsForm(startDate, endDate);
    }

    @PostMapping(value = "getPropagandaMaterialsProducedStatisticsForm")
    @ApiOperation(value = "统计宣传品制作表单")
    @ResponseBody
    public List<Map<String, Integer>> getPropagandaMaterialsProducedStatistics(@RequestBody PropagandaMaterialsProducedStatisticalQuery query) {
        return propagandaMaterialsProducedService.getPropagandaMaterialsProducedByApplicationDate(query);
    }

    @RequestMapping(value = "getPropagandaInformationStatisticsForm", method = RequestMethod.GET)
    @ApiOperation(value = "统计宣传信息发布统计报表")
    @ResponseBody
    public List<Map<String, Integer>> getPropagandaInformationStatisticsForm(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return PropagandaInformationService.getPropagandaInformationByApplicationDate(startDate, endDate);
    }

}
