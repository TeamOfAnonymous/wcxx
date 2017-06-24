package com.anonymous.repository.PropagandaWorkProgram;


import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPProject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目 dao层接口
 */
public interface PWPProjectRepository extends JpaRepository<PWPProject, String> {
}
