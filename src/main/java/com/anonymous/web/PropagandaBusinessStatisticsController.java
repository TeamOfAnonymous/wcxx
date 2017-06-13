package com.anonymous.web;

import com.anonymous.service.inter.PropagandaMaterialsRecipientsServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */
@Controller
@Api(value = "宣传事务统计", description = "提供宣传事务统计的服务")
public class PropagandaBusinessStatisticsController {

    @Autowired
    private PropagandaMaterialsRecipientsServiceInter propagandaMaterialsRecipientsService;

    @RequestMapping(value = "getPropagandaMaterialsRecipientsStatisticsForm", method = RequestMethod.GET)
    @ApiOperation(value = "宣传物资领用统计报表")
    @ResponseBody
    public Map<String, Integer> getPropagandaMaterialsRecipientsStatisticsForm(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return propagandaMaterialsRecipientsService.getPropagandaMaterialsRecipientsStatisticsForm(startDate, endDate);
    }

}
