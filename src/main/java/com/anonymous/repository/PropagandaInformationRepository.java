package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description：宣传信息Repository
 * Created by Peivxuan on 2017/5/26.
 */
@Repository
public interface PropagandaInformationRepository extends JpaRepository<PropagandaInformation, String> {
}
