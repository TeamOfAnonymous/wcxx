package com.anonymous.repository;

import com.anonymous.domain.PropagandaMaterialsProduced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huangMP on 2017/5/26.
 * decription :
 */
@Repository
public interface PropagandaMaterialsProducedRepository extends JpaRepository<PropagandaMaterialsProduced, String> {
}
