package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaInformation.PpgdaInfQueryCondition;
import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    List<PropagandaInformation> findByPropagandaInformationCategories(String id);

	Page<PropagandaInformation> findByQueryCondition(PpgdaInfQueryCondition condition);

	List<Map<String, Integer>> getPropagandaInformationByApplicationDate(LocalDate startDate, LocalDate endDate );

	boolean deletedPropagandaInformation(String[] ids) ;
	boolean filePropagandaInformation(String[] ids) ;
}
