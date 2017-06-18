package com.anonymous.service.inter;

import com.anonymous.domain.organization.Organization;
import com.anonymous.domain.organization.OrganizationTree;

import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
public interface OrganizationServiceInter {

    Organization add(Organization organization);

    List<Organization> find();

    List<Organization> findByPid(String pid);

    Organization findById(String id);

    Organization edit(Organization organization);

    boolean delete(String id);

    List<OrganizationTree> getOrganizationTree();
}
