package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaInformation;

/**
 * Description：宣传信息服务接口
 * Created by Peivxuan on 2017/5/26.
 */
public interface PropagandaInformationServiceInter {

	/**
	 * 保存
	 * @param propagandaInformation
	 * @return
	 */
	PropagandaInformation save(PropagandaInformation propagandaInformation);

	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	PropagandaInformation getById(String id);
}
