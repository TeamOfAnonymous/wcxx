package com.anonymous.service;

import com.anonymous.domain.PropagandaWorkSummary;
import com.anonymous.repository.PropagandaWorkSummaryRepository;
import com.anonymous.service.inter.PropagandaWorkSummaryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Shero on 2017-06-13.
 */
@Service
public class PropagandaWorkSummaryService implements PropagandaWorkSummaryServiceInter {

    @Autowired
    private PropagandaWorkSummaryRepository propagandaWorkSummaryRepository;

    @Override
    public PropagandaWorkSummary add(PropagandaWorkSummary propagandaWorkSummary) {
        return propagandaWorkSummaryRepository.save(propagandaWorkSummary);
    }

    @Override
    public PropagandaWorkSummary getById(String id) {
        return propagandaWorkSummaryRepository.findOne(id);
    }

    @Override
    public boolean delete(String id) {
        propagandaWorkSummaryRepository.delete(id);
        return true;
    }

    @Override
    public Page<PropagandaWorkSummary> getPropagandaWorkSummaryForPage(Integer currentPage, Integer size, String title, String draftMan, LocalDateTime draftDate, Integer approvalStatus) {
        Sort sort = new Sort(Sort.Direction.fromString("DESC"), "draftDate");
        Pageable pageable = new PageRequest(currentPage, size, sort);

        if (approvalStatus == 100) {
            if ("".equals(draftDate) || draftDate == null) {
                return propagandaWorkSummaryRepository.getPropagandaWorkSummaryForPage(title, draftMan, pageable);
            } else {
                return propagandaWorkSummaryRepository.getPropagandaWorkSummaryForPage(title, draftMan, draftDate, pageable);
            }
        } else {
            if ("".equals(draftDate) || draftDate == null) {
                return propagandaWorkSummaryRepository.getPropagandaWorkSummaryForPage(title, draftMan, approvalStatus, pageable);
            } else {
                return propagandaWorkSummaryRepository.getPropagandaWorkSummaryForPage(title, draftMan, draftDate, approvalStatus, pageable);
            }

        }


    }


}
