package com.anonymous.repository.PropagandaWorkProgram;


import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案分类 dao层接口
 */
public interface PWPCategoryRepository extends JpaRepository<PWPCategory, String> {
}
