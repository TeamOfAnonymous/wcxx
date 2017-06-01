package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZK on 2017/6/1.
 */
@Repository
public interface PropagandaInformationCategoryRepository extends JpaRepository<PropagandaInformationCategory, String> {
    PropagandaInformationCategory findByNameAndPid(String name, String pid);

    List<PropagandaInformationCategory> findByPid(String pid);

    List<PropagandaInformationCategory> findByPidIsNull();
}
