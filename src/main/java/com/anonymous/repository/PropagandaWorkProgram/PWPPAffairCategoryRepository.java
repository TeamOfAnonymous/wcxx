package com.anonymous.repository.PropagandaWorkProgram;


import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目分类 dao层接口
 */
public interface PWPPAffairCategoryRepository extends JpaRepository<PWPPAffairCategory, String> {
}
