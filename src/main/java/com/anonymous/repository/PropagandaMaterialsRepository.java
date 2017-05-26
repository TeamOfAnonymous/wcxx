package com.anonymous.repository;

import com.anonymous.domain.PropagandaMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZK on 2017/5/26.
 */
@Repository
public interface PropagandaMaterialsRepository extends JpaRepository<PropagandaMaterials, String> {
}
