package com.anonymous.repository.PropagandaMaterialsProduced;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by huangMP on 2017/6/10.
 * decription :
 */
public interface PropagandaMaterialsProducedRepositoryCustom {
    List<Object> findByQuery(PropagandaMaterialsProducedQuery query , int startIndex , int endIndex ) ;
    long countByQuery(PropagandaMaterialsProducedQuery query) ;
}
