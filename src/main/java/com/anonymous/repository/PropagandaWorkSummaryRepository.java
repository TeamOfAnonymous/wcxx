package com.anonymous.repository;

import com.anonymous.domain.PropagandaWorkSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Shero on 2017-06-13.
 */
@Repository
public interface PropagandaWorkSummaryRepository extends JpaRepository<PropagandaWorkSummary,String> {

    @Query("from PropagandaWorkSummary p where p.title like %?1% and p.draftMan.name like %?2%")
    Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(String title, String draftMan, Pageable pageable);

    @Query("from PropagandaWorkSummary p where p.title like %?1% and p.draftMan.name like %?2% and p.draftDate = ?3")
    Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(String title, String draftMan, LocalDate draftDate, Pageable pageable);

    @Query("from PropagandaWorkSummary p where p.title like %?1% and p.draftMan.name like %?2% and p.approvalStatus = ?3")
    Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(String title, String draftMan, Integer approvalStatus, Pageable pageable);

    @Query("from PropagandaWorkSummary p where p.title like %?1% and p.draftMan.name like %?2% and p.draftDate = ?3 and p.approvalStatus = ?4")
    Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(String title, String draftMan, LocalDate draftDate, Integer approvalStatus, Pageable pageable);


}
