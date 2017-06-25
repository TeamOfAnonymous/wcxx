package com.anonymous.repository;

import com.anonymous.domain.PlanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description：enter your comment
 * Created by Peivxuan on 2017/6/26.
 */
@Repository
public interface PlanProjectRepository extends JpaRepository<PlanProject, String> {

}
