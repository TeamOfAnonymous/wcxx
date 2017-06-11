package com.anonymous.repository;

import com.anonymous.domain.PropagandaMaterialsRecipients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("from PropagandaMaterialsRecipients p where p.title like %?1% and p.applicant.name like %?2%")
    Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(String title, String applicant, Pageable pageable);

    @Query("from PropagandaMaterialsRecipients p where p.title like %?1% and p.applicant.name like %?2% and p.approvalStatus = ?3")
    Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(String title, String applicant, int approvalStatus, Pageable pageable);

    @Query("from PropagandaMaterialsRecipients p where p.title like %?1% and p.applicant.name like %?2% and p.applicationDate = ?3")
    Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(String title, String applicant, LocalDate applicationDate, Pageable pageable);

    @Query("from PropagandaMaterialsRecipients p where p.title like %?1% and p.applicant.name like %?2% and p.applicationDate = ?3 and p.approvalStatus = ?4")
    Page<PropagandaMaterialsRecipients> getPropagandaMaterialsRecipientsForPage(String title, String applicant, LocalDate applicationDate, int approvalStatus, Pageable pageable);


    @Query("from PropagandaMaterialsRecipients p where p.applicationDate between ?1 and ?2")
    List<PropagandaMaterialsRecipients> findByApplicationDate(LocalDate startDate, LocalDate endDate);

    @Query("from PropagandaMaterialsRecipients p where p.applicant.id = ?1 and (p.applicationDate between ?2 and ?3)")
    List<PropagandaMaterialsRecipients> findByApplicantAndApplicationDate(String id, LocalDate startDate, LocalDate endDate);

    @Query("from PropagandaMaterialsRecipients p where p.applicant.id = ?1")
    List<PropagandaMaterialsRecipients> findByApplicant(String id);
}
