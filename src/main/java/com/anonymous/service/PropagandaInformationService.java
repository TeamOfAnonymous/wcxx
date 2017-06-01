package com.anonymous.service;

import com.anonymous.domain.Organization;
import com.anonymous.domain.PropagandaInformation;
import com.anonymous.domain.PropagandaInformationCategory;
import com.anonymous.repository.PropagandaInformationRepository;
import com.anonymous.service.inter.OrganizationServiceInter;
import com.anonymous.service.inter.PropagandaInformationCategoryServiceInter;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private PropagandaInformationCategoryServiceInter propagandaInformationCategoryService;

	/**
	 * 保存
	 *
	 * @param propagandaInformation
	 * @return
	 */
	@Override
	public PropagandaInformation save(PropagandaInformation propagandaInformation) {
		return propagandaInformationRepository.save(propagandaInformation);
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
	public List<PropagandaInformation> findByPropagandaInformationCategory(String propagandaInformationCategory_id) {
		PropagandaInformationCategory propagandaInformationCategory = propagandaInformationCategoryService.findById(propagandaInformationCategory_id);
		return propagandaInformationRepository.findByPropagandaInformationCategory(propagandaInformationCategory);
	}

}
