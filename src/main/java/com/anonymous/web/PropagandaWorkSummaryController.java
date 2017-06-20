package com.anonymous.web;

import com.anonymous.domain.PropagandaWorkSummary;
import com.anonymous.service.inter.PropagandaWorkSummaryServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Created by Shero on 2017-06-13.
 */
@Controller
@Api(value = "宣传工作总结")
public class PropagandaWorkSummaryController {

    @Autowired
    private PropagandaWorkSummaryServiceInter propagandaWorkSummaryService;

    @RequestMapping(value = "addPropagandaWorkSummary",method = RequestMethod.POST)
    @ApiOperation(value = "添加宣传工作总结")
    @ResponseBody
    public PropagandaWorkSummary addPropagandaWorkSummary(@RequestBody PropagandaWorkSummary propagandaWorkSummary){
        return propagandaWorkSummaryService.add(propagandaWorkSummary);
    }

    @RequestMapping(value = "getPropagandaWorkSummaryById",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取宣传工作总结")
    @ResponseBody
    public PropagandaWorkSummary getById(@RequestParam String id){
        return propagandaWorkSummaryService.getById(id);
    }

    @RequestMapping(value = "deletePropagandaWorkSummary",method = RequestMethod.GET)
    @ApiOperation(value = "删除宣传工作总结")
    @ResponseBody
    public boolean deletePropagandaWorkSummary(String[] ids){
        return propagandaWorkSummaryService.delete(ids);
    }

    @RequestMapping(value = "filePropagandaWorkSummary",method = RequestMethod.GET)
    @ApiOperation(value = "归档宣传工作总结")
    @ResponseBody
    public boolean filePropagandaWorkSummary(String[] ids){
        return propagandaWorkSummaryService.file(ids);
    }

    @RequestMapping(value = "getPropagandaWorkSummaryForPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页查询宣传工作总结")
    @ResponseBody
    public Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(@RequestParam Integer currentPage,
                                                                       @RequestParam Integer size,
                                                                       @RequestParam String title,
                                                                       @RequestParam String draftMan,
                                                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate draftDate,
                                                                       @RequestParam Integer approvalStatus){
        return propagandaWorkSummaryService.getPropagandaWorkSummaryForPage(currentPage,size,title,draftMan,draftDate,approvalStatus);

    }




}
