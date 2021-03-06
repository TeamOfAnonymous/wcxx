package com.anonymous.web;

import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.service.PpgdaInfStatisticsService;
import com.anonymous.service.inter.PpgdaInfStatisticsServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/5/26.
 */
@Controller
@Api(value = "宣传信息管理")
public class PropagandaInformationController {

	@Autowired
	private PropagandaInformationServiceInter propagandaInformationService;

	@Autowired
	private PpgdaInfStatisticsServiceInter ppgdaInfStatisticsService;

	@ResponseBody
	@ApiOperation(value = "添加宣传信息")
	@RequestMapping(value = "addPropagandaInformation", method = RequestMethod.POST)
	public PropagandaInformation save(@RequestBody PropagandaInformation propagandaInformation){
		return  propagandaInformationService.save(propagandaInformation);
	}

	@RequestMapping(value = "getPropagandaInformationByQueryCondition", method = RequestMethod.POST)
	@ApiOperation(value = "宣传信息查询")
	@ResponseBody
	public Page propagandaMaterialsProducedQuery(@RequestBody PpgdaInfQueryCondition condition) {
		return propagandaInformationService.findByQueryCondition(condition);
	}

	@ResponseBody
	@ApiOperation(value = "通过id获取宣传信息")
	@RequestMapping(value = "getPropagandaInformationById/{id}", method = RequestMethod.GET)
	public PropagandaInformation getById(@PathVariable String id){
		return  propagandaInformationService.getById(id);
	}

	@RequestMapping(value = "deletedPropagandaInformation", method = RequestMethod.GET)
	@ApiOperation(value = "通过id删除宣传信息")
	@ResponseBody
	public boolean deletedPropagandaInformation(String[] ids) {
		return propagandaInformationService.deletedPropagandaInformation(ids);
	}

	@RequestMapping(value = "filePropagandaInformation", method = RequestMethod.GET)
	@ApiOperation(value = "通过id归档宣传信息")
	@ResponseBody
	public boolean filePropagandaInformation(String[] ids) {
		return propagandaInformationService.filePropagandaInformation(ids);
	}

	@ResponseBody
	@ApiOperation(value = "获取统计信息")
	@RequestMapping(value = "getStatisticsData", method = RequestMethod.GET)
	public PpgdaInfStatisticsService.StatisticsData getStatisticsData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		return ppgdaInfStatisticsService.getStatisticsData(startDate, endDate);
	}


}
