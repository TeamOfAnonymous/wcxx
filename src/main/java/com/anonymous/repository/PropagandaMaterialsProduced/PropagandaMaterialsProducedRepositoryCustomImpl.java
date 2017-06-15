package com.anonymous.repository.PropagandaMaterialsProduced;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import com.anonymous.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangMP on 2017/6/10.
 * decription :
 */
@Component
public class PropagandaMaterialsProducedRepositoryCustomImpl implements PropagandaMaterialsProducedRepositoryCustom {
    /**
     * EntityManager
     */
    private final EntityManager em;
    /**
     * CriteriaBuilder
     */
    private CriteriaBuilder builder ;
    /**
     * CriteriaQuery 根据 query 产生的 sql (只有 where 没有 其他检索条件，例如 orderBy)
     */
    private CriteriaQuery<Object> sqlQuery ;
    /**
     * 主查询表
     */
    private Root<PropagandaMaterialsProduced> pmpRoot ;

    @Autowired
    public PropagandaMaterialsProducedRepositoryCustomImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(PropagandaMaterialsProduced.class);
    }


    /**
     * 初始化 builder sqlQuery pmpRoot
     * @param query
     */
    public void init(PropagandaMaterialsProducedQuery query){

        builder = em.getCriteriaBuilder();
        sqlQuery = builder.createQuery();

        // 构造 sql 语句  -- start
        // 主查询表
        pmpRoot = sqlQuery.from( PropagandaMaterialsProduced.class );

        // 设置关联表
        // 设置 PropagandaMaterialsProduced.applicant 对应 User
        Join<PropagandaMaterialsProduced, User> pmpAndUser = pmpRoot.join("applicant", JoinType.INNER);
        // 设置 PropagandaMaterialsProduced.propagandaMaterialsContents 对应 propagandaMaterialsContents
        Join<PropagandaMaterialsProduced, PropagandaMaterialsContent> pmpAndPmc = pmpRoot.join("propagandaMaterialsContents", JoinType.INNER);

        User applicant = null;
        if( query.getPropagandaMaterialsProduced() != null ){
            // 获取查询 Query 中的申请人
            applicant = query.getPropagandaMaterialsProduced().getApplicant();
        }
        // 获取查询 Query 中的宣传品
        PropagandaMaterialsProduced ppm = query.getPropagandaMaterialsProduced();

        List<Predicate> predicates = new ArrayList<>();
        // 对 PropagandaMaterialsProduced.applicant.name 进行模糊查询
        if( applicant != null && applicant.getName() != null ){
            predicates.add( builder.like(pmpAndUser.get("name"), "%" + query.getPropagandaMaterialsProduced().getApplicant().getName() + "%") );
        }
        // 对 宣传品标题 进行模糊查询
        if( ppm != null && ppm.getTitle() != null ){
            builder.like(pmpRoot.get("title"), "%" + ppm.getTitle() + "%") ;
        }
        // 对 宣传品状态 进行精准查询
        if( ppm != null && ppm.getApprovalStatus() != null ){
            predicates.add( builder.equal(pmpRoot.get("approvalStatus"), ppm.getApprovalStatus()) );
        }
        // 根据 Query 的 maxTotalCost 和 minTotalCost  对 宣传品totalCost 进行范围查询
        if( query.getMaxTotalCost() > 0 ){
            predicates.add( builder.between(pmpRoot.get("totalCost"), query.getMinTotalCost(), query.getMaxTotalCost()) ) ;
        }else {
            predicates.add( builder.greaterThanOrEqualTo(pmpRoot.get("totalCost"), query.getMinTotalCost()) ) ;
        }
        // 根据 Query 的 productionMethod  对 宣传内容 productionMethod 进行精准查询
        if( query.getProductionMethod() != null ){
            predicates.add( builder.equal(pmpAndPmc.get("productionMethod"), query.getProductionMethod()) );
        }

        Predicate[] pre = new Predicate[predicates.size()];
        predicates.toArray(pre);


        sqlQuery
                .where(
                        pre
                );
        // 构造 sql 语句  -- end
    }

    /**
     * 通过 query 查询
     * @param query
     * @return
     */
    public List<Object> findByQuery(PropagandaMaterialsProducedQuery query , int startIndex , int endIndex ) {

        init(query);

        // 执行查询
        List<Object> resultList = em.createQuery(
                sqlQuery.select(pmpRoot)
                        .distinct(true) // 去重复项
                        .orderBy( // 设置 排序列 排序规则
                                builder.desc(pmpRoot.get("applicationDate")),
                                builder.desc(pmpRoot.get("totalCost"))
                        )
        )
                .setFirstResult( startIndex).setMaxResults( endIndex) // 分页查找 LIMIT ?, ?
                .getResultList();


        // 返回结果
        return  resultList ;
    }

    /**
     * 通过 query 查询 符合条件的总个数
     * @param query
     * @return
     */
    public long countByQuery(PropagandaMaterialsProducedQuery query){
        init(query);
        return  (long) em.createQuery(
                sqlQuery
                        .distinct(true) // 去重复项
                        .select((builder.countDistinct(pmpRoot))
                        )
        ).getSingleResult();
    }

}



