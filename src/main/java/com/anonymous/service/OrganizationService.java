package com.anonymous.service;

import com.anonymous.domain.organization.Organization;
import com.anonymous.domain.User;
import com.anonymous.domain.organization.OrganizationTree;
import com.anonymous.repository.OrganizationRepository;
import com.anonymous.service.inter.OrganizationServiceInter;
import com.anonymous.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/5/25.
 */
@Service
public class OrganizationService implements OrganizationServiceInter {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserServiceInter userService;

    @Override
    public Organization add(Organization organization) {
        //根据组织机构名称和父节点进行查询
        Organization organization1 = organizationRepository.findByNameAndPid(organization.getName(), organization.getPid());
        //如果直接返回，不存在再进行保存
        if (organization1 != null) {
            return organization1;
        }
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> find() {
        return organizationRepository.findByPidIsNull();
    }

    @Override
    public List<Organization> findByPid(String pid) {
        return organizationRepository.findByPid(pid);
    }

    @Override
    public Organization findById(String id) {
        return organizationRepository.findOne(id);
    }

    @Override
    public Organization edit(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public boolean delete(String id) {
        //判断是否存在子节点，如果存在，不给删除，如果不存在，则可以删除
        List<Organization> organizations = organizationRepository.findByPid(id);
        if (organizations.size() > 0) {
            return false;
        } else {
            List<User> users = userService.findByOrganization(id);
            if (users.size() > 0) {
                return false;
            }
        }
        organizationRepository.delete(id);
        return true;
    }

    //将所有的组织机构遍历成树
    @Override
    public List<OrganizationTree> getOrganizationTree() {
        List<OrganizationTree> organizationTreeList = new ArrayList<>();
        //获取父节点为空的组织机构
        List<Organization> organizations = organizationRepository.findByPidIsNull();
        for (Organization o : organizations) {
            OrganizationTree organizationTree = new OrganizationTree(o);
            //调用获取子节点的方法
            getOrganizationSon(organizationTree);
            organizationTreeList.add(organizationTree);
        }
        return organizationTreeList;
    }

    //递归获取组织机构的子节点
    private void getOrganizationSon(OrganizationTree organizationTree) {
        List<Organization> organizations = organizationRepository.findByPid(organizationTree.getId());
        List<OrganizationTree> organizationTreeList = null;
        if (organizations.size() > 0) {
            organizationTreeList = new ArrayList<>();
            for(Organization o : organizations) {
                OrganizationTree sonOrganizationTree = new OrganizationTree(o);
                organizationTreeList.add(sonOrganizationTree);
                getOrganizationSon(sonOrganizationTree);
            }
        }
        organizationTree.setNodes(organizationTreeList);
    }

}
