package com.anonymous.service;

import com.anonymous.domain.PlanProject;
import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaWorkPlan;
import com.anonymous.repository.PlanProjectRepository;
import com.anonymous.repository.PropagandaWorkPlanRepository;
import com.anonymous.service.inter.PropagandaWorkPlanServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/25.
 */
@Service
public class PropagandaWorkPlanService implements PropagandaWorkPlanServiceInter {

	@Autowired
	private PropagandaWorkPlanRepository propagandaWorkPlanRepository;

	@Autowired
	private PlanProjectRepository planProjectRepository;

	@Override
	public PropagandaWorkPlan add(PropagandaWorkPlan propagandaWorkPlan) {
		System.out.println(propagandaWorkPlan);
		//PropagandaWorkPlan newPropagandaWorkPlan = propagandaWorkPlanRepository.save(propagandaWorkPlan);
//		List<PlanProject> planProjects = propagandaWorkPlan.getPlanProjects();
//		for(PlanProject p : planProjects) {
//			p.setPropagandaWorkPlan(propagandaWorkPlan);
//		}
//		propagandaWorkPlan.setPlanProjects(planProjects);
		return propagandaWorkPlanRepository.save(propagandaWorkPlan);
	}

	@Override
	public Page<PropagandaWorkPlan> findByQueryCondition(PpgdaInfQueryCondition condition) {

		System.out.println(condition.getPropagandaWorkPlan());

		PageRequest pageRequest = new PageRequest(condition.getCurrentPage() - 1, condition.getLimit());

		if (null != condition.getPropagandaWorkPlan()) {

			if ("".equals(condition.getPropagandaWorkPlan().getApprovalStatus()))
				condition.getPropagandaWorkPlan().setApprovalStatus(null);
			if ("".equals(condition.getPropagandaWorkPlan().getPlanTransactionCategory()))
				condition.getPropagandaWorkPlan().setPlanTransactionCategory(null);
			if ("".equals(condition.getPropagandaWorkPlan().getDraftMan().getName()))
				condition.getPropagandaWorkPlan().setDraftMan(null);

			ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains)
					.withMatcher("draftMan.name", ExampleMatcher.GenericPropertyMatcher::contains)
					.withMatcher("draftDate", ExampleMatcher.GenericPropertyMatcher::exact)
					.withMatcher("planTransactionCategory", ExampleMatcher.GenericPropertyMatcher::exact)
					.withMatcher("approvalStatus", ExampleMatcher.GenericPropertyMatcher::exact)
					.withIgnorePaths("id", "planCategory", "groupName", "principal", "startExecuteTime",
							"endExecuteTime", "members", "content", "remarks", "planProjects")
					.withIgnoreNullValues();

			Example<PropagandaWorkPlan> example = Example.of(condition.getPropagandaWorkPlan(), matcher);

			return this.propagandaWorkPlanRepository.findAll(example, pageRequest);
		} else {
			return this.propagandaWorkPlanRepository.findAll(pageRequest);
		}

	}

}
