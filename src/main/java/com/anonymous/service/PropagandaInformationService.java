package com.anonymous.service;

import com.anonymous.domain.PpgdaInfStatistics;
import com.anonymous.domain.PropagandaInformation;
import com.anonymous.domain.PropagandaInformationCategory;
import com.anonymous.repository.PpgdaInfStatisticsRepository;
import com.anonymous.repository.PropagandaInformationRepository;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
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
	private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

	/**
	 * 保存
	 *
	 * @param propagandaInformation
	 * @return
	 */
	@Override
	public PropagandaInformation save(PropagandaInformation propagandaInformation) {
		PropagandaInformation back = propagandaInformationRepository.save(propagandaInformation);
		//TODO 将back摘要保存到PpgdaInfStatistics中
		List<PropagandaInformationCategory> backCategories = back.getPropagandaInformationCategories();
		List<PpgdaInfStatistics> ppgdaInfStatisticsList = new ArrayList<>(backCategories.size());
		PpgdaInfStatistics ppgdaInfStatistics;
		//将分类存储多条到统计表中
		for ( PropagandaInformationCategory temp : backCategories ) {
			ppgdaInfStatistics= new PpgdaInfStatistics();
			ppgdaInfStatistics.setUserId(back.getApplicant().getId());
			ppgdaInfStatistics.setCreateTime(back.getApplicationDate().toLocalDate());
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
		PropagandaInformationCategory propagandaInformationCategory = propagandaInformationCategoryService.findById(propagandaInformationCategory_id);
		return propagandaInformationRepository.findByPropagandaInformationCategories(propagandaInformationCategory);
	}

}
