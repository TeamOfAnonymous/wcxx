package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsProduced;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品制作申请 Service层接口
 */
public interface PropagandaMaterialsProducedServiceInter {

    PropagandaMaterialsProduced findById(String id) ;

    PropagandaMaterialsProduced add(PropagandaMaterialsProduced propagandaMaterialsProduced ,String  propagandaInformationId) ;

    PropagandaMaterialsProduced apply(String id) ;
}
