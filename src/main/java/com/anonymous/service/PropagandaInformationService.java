package com.anonymous.service;

import com.anonymous.domain.PropagandaInformation;
import com.anonymous.repository.PropagandaInformationRepository;
import com.anonymous.service.inter.PropagandaInformationServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：宣传信息服务实现类
 * Created by Peivxuan on 2017/5/26.
 */
@Service
public class PropagandaInformationService implements PropagandaInformationServiceInter {

	@Autowired
	private PropagandaInformationRepository propagandaInformationRepository;

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

}
