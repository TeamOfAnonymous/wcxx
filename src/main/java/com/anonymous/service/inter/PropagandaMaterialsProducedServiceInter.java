package com.anonymous.service.inter;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsProduced.dto.PropagandaMaterialsProducedStatisticalDto;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品制作申请 Service层接口
 */
public interface PropagandaMaterialsProducedServiceInter {

    PropagandaMaterialsProduced findById(String id) ;

    PropagandaMaterialsProduced add(PropagandaMaterialsProduced p ) ;

    PropagandaMaterialsProduced update(PropagandaMaterialsProduced p ) ;

    PropagandaMaterialsProduced apply(String id) ;

    boolean delete(String[] ids) ;

    Page findByMultiExample(PropagandaMaterialsProducedQuery query) ;

    Page findByQuery(PropagandaMaterialsProducedQuery query) ;

    PropagandaMaterialsProducedStatisticalDto statisticalQuery(PropagandaMaterialsProducedStatisticalQuery query) ;

    Page findBySpecification(PropagandaMaterialsProducedQuery query) ;

    List<Map<String, Integer>> getPropagandaMaterialsProducedByApplicationDate(PropagandaMaterialsProducedStatisticalQuery query);
}
