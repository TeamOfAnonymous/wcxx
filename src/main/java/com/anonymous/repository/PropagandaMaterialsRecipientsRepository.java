package com.anonymous.repository;

import com.anonymous.domain.PropagandaMaterials;
import com.anonymous.domain.PropagandaMaterialsRecipients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by WangZK on 2017/5/26.
 */
@Repository
public interface PropagandaMaterialsRecipientsRepository extends JpaRepository<PropagandaMaterialsRecipients, String> {

    @Query("from PropagandaMaterialsRecipients p where p.applicationDate between ?1 and ?2")
    List<PropagandaMaterialsRecipients> findByApplicationDate(LocalDate startDate, LocalDate endDate);

    @Query("from PropagandaMaterialsRecipients p where p.applicant.id = ?1 and (p.applicationDate between ?2 and ?3)")
    List<PropagandaMaterialsRecipients> findByApplicantAndApplicationDate(String id, LocalDate startDate, LocalDate endDate);

    @Query("from PropagandaMaterialsRecipients p where p.applicant.id = ?1")
    List<PropagandaMaterialsRecipients> findByApplicant(String id);
}
