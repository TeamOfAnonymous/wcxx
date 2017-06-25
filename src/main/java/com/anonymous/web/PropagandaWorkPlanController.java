package com.anonymous.web;

import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaWorkPlan;
import com.anonymous.service.inter.PropagandaWorkPlanServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/25.
 */
@Controller
@Api(value = "工作计划管理")
public class PropagandaWorkPlanController {

	@Autowired
	private PropagandaWorkPlanServiceInter propagandaWorkPlanService;

	@RequestMapping(value = "addPropagandaWorkPlan", method = RequestMethod.POST)
	@ApiOperation(value = "添加宣传工作计划")
	@ResponseBody
	public PropagandaWorkPlan addPropagandaWorkPlan(@RequestBody PropagandaWorkPlan propagandaWorkPlan) {
		return propagandaWorkPlanService.add(propagandaWorkPlan);
	}

	@RequestMapping(value = "getPropagandaWorkPlanByQueryCondition", method = RequestMethod.POST)
	@ApiOperation(value = "宣传工作计划查询")
	@ResponseBody
	public Page<PropagandaWorkPlan> propagandaMaterialsProducedQuery(@RequestBody PpgdaInfQueryCondition ppgdaInfQueryCondition) {
		return propagandaWorkPlanService.findByQueryCondition(ppgdaInfQueryCondition);
	}

}
