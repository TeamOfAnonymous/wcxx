package com.anonymous.repository;

import com.anonymous.domain.Organization;
import com.anonymous.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByOrganization(Organization organization);
}
