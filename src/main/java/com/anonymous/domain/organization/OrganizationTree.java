package com.anonymous.domain.organization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/18.
 */
public class OrganizationTree {

    private String id;
    private String text;
    private String pid;
    private List<OrganizationTree> nodes;

    public OrganizationTree() {

    }

    public OrganizationTree(Organization organization) {
        this.id = organization.getId();
        this.text = organization.getName();
        this.pid = organization.getPid();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<OrganizationTree> getNodes() {
        return nodes;
    }

    public void setNodes(List<OrganizationTree> nodes) {
        this.nodes = nodes;
    }
}
