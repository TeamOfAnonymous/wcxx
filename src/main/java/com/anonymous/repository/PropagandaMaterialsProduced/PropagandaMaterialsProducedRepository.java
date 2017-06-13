package com.anonymous.repository.PropagandaMaterialsProduced;

import com.anonymous.domain.PropagandaMaterialsProduced.ApprovalStatus;
import com.anonymous.domain.PropagandaMaterialsProduced.ProductionProducedMethod;
import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription :
 */
@Repository
public interface PropagandaMaterialsProducedRepository
        extends
        JpaRepository<PropagandaMaterialsProduced, String> ,
        JpaSpecificationExecutor
{

    /**
     * 通过 开始日期 和 最后日期 查询 宣传品
     * @param applicationDateStart
     * @param applicationDateEnd
     * @return
     */
    List<PropagandaMaterialsProduced> findByApplicationDateBetween(LocalDateTime applicationDateStart ,LocalDateTime applicationDateEnd );

}

