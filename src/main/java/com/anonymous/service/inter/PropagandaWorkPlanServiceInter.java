package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaWorkPlan;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/25.
 */
public interface PropagandaWorkPlanServiceInter {
	PropagandaWorkPlan add(PropagandaWorkPlan propagandaWorkPlan);

	Page<PropagandaWorkPlan> findByQueryCondition (PpgdaInfQueryCondition ppgdaInfQueryCondition);

}
