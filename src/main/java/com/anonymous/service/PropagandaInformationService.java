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

import java.time.LocalDate;
import java.util.*;

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
		propagandaInformation.setApprovalStatus(0);
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

	//删除
	@Override
	public boolean deletedPropagandaInformation(String[] ids) {
		for (String id : ids) {
			PropagandaInformation propagandaInformation = getById(id);
			//判断是否可以删除
			if (propagandaInformation.getApprovalStatus() > 0) {
				return false;
			}
		}
		for (String id : ids) {
			if (!deleted(id)) {
				throw new RuntimeException("删除 宣传信息 出现异常 ");
			}
		}
		return true;
	}

	private boolean deleted(String id) {
		PropagandaInformation propagandaInformation = getById(id);
		if (propagandaInformation != null) {
			propagandaInformationRepository.delete(id);
		}
		return propagandaInformationRepository.findOne(id) == null ? true : false;
	}

	//归档
	@Override
	public boolean filePropagandaInformation(String[] ids) {
		for (String id : ids) {
			PropagandaInformation propagandaInformation = getById(id);
			//判断是否可以归档
			if (propagandaInformation.getApprovalStatus() != 3) {
				return false;
			}
		}
		for (String id : ids) {
			PropagandaInformation propagandaInformation = getById(id);
			propagandaInformation.setApprovalStatus(4);
			propagandaInformationRepository.save(propagandaInformation);
		}
		return true;
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

	@Override
	public List<Map<String, Integer>> getPropagandaInformationByApplicationDate(LocalDate startDate, LocalDate endDate ) {
		//找出时间间隔内宣传信息发布
		List<PropagandaInformation> propagandaInformations1 = propagandaInformationRepository.findByApplicationDateBetween(startDate,endDate);
		//获取宣传信息发布的宣传类别
		Set<String> propagandaInformationCategories = new HashSet<>();
		for (PropagandaInformation propagandaInformation : propagandaInformations1){
			for (PropagandaInformationCategory propagandaInformationCategory : propagandaInformation.getPropagandaInformationCategories()) {
				propagandaInformationCategories.add(propagandaInformationCategory.getName());
			}
		}
		//根据宣传类别统计宣传信息发布的所有数量
		Map<String, Integer> statisticsTotalData = statisticsData(propagandaInformations1, propagandaInformationCategories);
		//获取已经完成的宣传信息发布
		int approvalStatus = 3;
		List<PropagandaInformation> propagandaInformations2 = propagandaInformationRepository.findByApplicationDateBetweenAndApprovalStatus(startDate,endDate,approvalStatus);
		Map<String, Integer> statisticsPracticalData = statisticsData(propagandaInformations2, propagandaInformationCategories);
		List<Map<String, Integer>> data = new ArrayList<>();
		data.add(statisticsTotalData);
		data.add(statisticsPracticalData);
		System.out.println(data);
		return data;
	}

	private Map<String,Integer> statisticsData(List<PropagandaInformation> propagandaInformations, Set<String> propagandaInformationCategories) {
		Map<String, Integer> statisticsTotalData = new LinkedHashMap<>();
		for (String category : propagandaInformationCategories) {
			int total = 0;
			for (PropagandaInformation propagandaInformation : propagandaInformations) {
				for (PropagandaInformationCategory propagandaInformationCategory : propagandaInformation.getPropagandaInformationCategories()) {
					if (category.equals(propagandaInformationCategory.getName())) {
						total = total + 1;
					}
				}
			}
			statisticsTotalData.put(category, total);
		}
		return statisticsTotalData;
	}

}
