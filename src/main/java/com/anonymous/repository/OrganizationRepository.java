package com.anonymous.repository;

import com.anonymous.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findByNameAndPid(String name, String pid);

    List<Organization> findByPidIsNull();

    List<Organization> findByPid(String id);
}
