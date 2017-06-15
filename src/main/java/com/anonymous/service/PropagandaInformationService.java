package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaInformation.PpgdaInfStatistics;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import com.anonymous.repository.PpgdaInfStatisticsRepository;
import com.anonymous.repository.PropagandaInformationCategoryRepository;
import com.anonymous.repository.PropagandaInformationRepository;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：宣传信息服务实现类
 * Created by Peivxuan on 2017/5/26.
 */
@Service
public class PropagandaInformationService implements PropagandaInformationServiceInter {

	@Autowired
	private PropagandaInformationRepository propagandaInformationRepository;
	@Autowired
	private PpgdaInfStatisticsRepository ppgdaInfStatisticsRepository;
	@Autowired
	private PropagandaInformationCategoryRepository propagandaInformationCategoryRepository;

	/**
	 * 保存
	 *
	 * @param propagandaInformation
	 * @return
	 */
	@Override
	public PropagandaInformation save(PropagandaInformation propagandaInformation) {
		PropagandaInformation back = propagandaInformationRepository.save(propagandaInformation);
		back = propagandaInformationRepository.findOne(back.getId());
		// --- 得到分类id的list ----
		List<String> categoryIds = new ArrayList<>(back.getPropagandaInformationCategories().size());
		for (PropagandaInformationCategory p : back.getPropagandaInformationCategories())
			categoryIds.add(p.getId());
		// ---  ----
		List<PropagandaInformationCategory> backCategories = propagandaInformationCategoryRepository.findAll(categoryIds);
		List<PpgdaInfStatistics> ppgdaInfStatisticsList = new ArrayList<>(backCategories.size());
		PpgdaInfStatistics ppgdaInfStatistics;
		//将分类存储多条到统计表中
		for (PropagandaInformationCategory temp : backCategories) {
			//TODO 将back摘要保存到PpgdaInfStatistics中
			ppgdaInfStatistics = new PpgdaInfStatistics();
			//ppgdaInfStatistics.setUserId(back.getApplicant().getId());
			//ppgdaInfStatistics.setCreateTime(back.getApplicationDate().toLocalDate());
			ppgdaInfStatistics.setMainCategoryId(temp.getId());
			ppgdaInfStatisticsList.add(ppgdaInfStatistics);
		}
		ppgdaInfStatisticsRepository.save(ppgdaInfStatisticsList);
		return back;
	}

	/**
	 * 通过id获取
	 *
	 * @param id
	 * @return
	 */
	@Override
	public PropagandaInformation getById(String id) {
		return propagandaInformationRepository.findOne(id);
	}

	@Override
	public List<PropagandaInformation> findByPropagandaInformationCategories(String propagandaInformationCategory_id) {
		PropagandaInformationCategory propagandaInformationCategory = propagandaInformationCategoryRepository.findOne(propagandaInformationCategory_id);
		return propagandaInformationRepository.findByPropagandaInformationCategories(propagandaInformationCategory);
	}

	@Override
	public Page<PropagandaInformation> findByQueryCondition(PpgdaInfQueryCondition condition) {

		PageRequest pageRequest = new PageRequest(condition.getCurrentPage() - 1, condition.getLimit(),
				new Sort(Sort.Direction.DESC, condition.getSortName()));

		if (null != condition.getPropagandaInformation()) {
			ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains)
					.withMatcher("applicant.name", ExampleMatcher.GenericPropertyMatcher::contains)
					.withMatcher("applicationDate", ExampleMatcher.GenericPropertyMatcher::exact)
					.withMatcher("propagandaInformationCategories[0].name", ExampleMatcher.GenericPropertyMatcher::exact)
					.withMatcher("approvalStatus", ExampleMatcher.GenericPropertyMatcher::exact)
					.withIgnorePaths("id", "startDate", "endDate", "content", "remarks", "propagandaInformationCategories")
					.withIgnoreNullValues();

			Example<PropagandaInformation> example = Example.of(condition.getPropagandaInformation(), matcher);

			return this.propagandaInformationRepository.findAll(example, pageRequest);
		} else {
			return this.propagandaInformationRepository.findAll(pageRequest);
		}

	}

}
