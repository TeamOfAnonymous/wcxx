package com.anonymous.repository;

import com.anonymous.domain.PropagandaInformation.PropagandaInformation;
import com.anonymous.domain.PropagandaInformation.PropagandaInformationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Description：宣传信息Repository
 * Created by Peivxuan on 2017/5/26.
 */
@Repository
public interface PropagandaInformationRepository extends JpaRepository<PropagandaInformation, String> {
    List<PropagandaInformation> findByPropagandaInformationCategories(PropagandaInformationCategory propagandaInformationCategory);

    List<PropagandaInformation> findByApplicationDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("from PropagandaInformation p where (p.applicationDate between ?1 and ?2) and p.approvalStatus >= ?3")
    List<PropagandaInformation> findByApplicationDateBetweenAndApprovalStatus(LocalDate startDate, LocalDate endDate, int approvalStatus);
}
