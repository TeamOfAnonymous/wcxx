package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZK on 2017/6/1.
 */
@Repository
public interface PropagandaInformationCategoryRepository extends JpaRepository<PropagandaInformationCategory, String> {
}
