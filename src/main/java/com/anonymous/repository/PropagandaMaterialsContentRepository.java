package com.anonymous.repository;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品材料内容 dao层接口
 */
public interface PropagandaMaterialsContentRepository extends JpaRepository<PropagandaMaterialsContent, String> {
}
