package com.anonymous.service.inter;

import com.anonymous.domain.Organization;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
public interface OrganizationServiceInter {

    Organization add(Organization organization);

    List<Organization> find();

    List<Organization> findByPid(String pid);

    Organization findById(String id);
}
