package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsContent;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品内容 Service层接口
 */
public interface PropagandaMaterialsContentServiceInter {

    PropagandaMaterialsContent findById(String id) ;

    PropagandaMaterialsContent add(PropagandaMaterialsContent propagandaMaterialsContent) ;
}
