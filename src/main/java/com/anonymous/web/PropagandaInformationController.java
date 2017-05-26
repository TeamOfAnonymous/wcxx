package com.anonymous.web;

import com.anonymous.domain.PropagandaInformation;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/5/26.
 */
@Controller
@Api(value = "宣传信息管理")
public class PropagandaInformationController {

	@Autowired
	private PropagandaInformationServiceInter propagandaInformationService;

	@ResponseBody
	@ApiOperation(value = "添加宣传信息")
	@RequestMapping(value = "addPropagandaInformation", method = RequestMethod.POST)
	public PropagandaInformation save(@RequestBody PropagandaInformation propagandaInformation){
		return  propagandaInformationService.save(propagandaInformation);
	}

	@ResponseBody
	@ApiOperation(value = "通过id获取宣传信息")
	@RequestMapping(value = "getPropagandaInformationById", method = RequestMethod.GET)
	public PropagandaInformation getById(@RequestParam String id){
		return  propagandaInformationService.getById(id);
	}

}
