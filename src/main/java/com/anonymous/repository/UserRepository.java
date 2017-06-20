package com.anonymous.repository;

import com.anonymous.domain.organization.Organization;
import com.anonymous.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByOrganization(Organization organization);

    @Query("from User u where u.name like %?1% and u.sex like %?2% and u.organization.id like %?3% and u.post like %?4% and u.status = ?5")
    Page<User> findByNameAndSexAndOrganizationAndPost(String name, String sex, String organization, String post, Integer status, Pageable pageable);
}
