package com.anonymous.domain.PropagandaMaterialsProduced.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangMP on 2017/6/11.
 * decription :
 */
public class PropagandaMaterialsProducedStatisticalDto {
    private List<String> header ;
    private List<List<String>> content = new ArrayList<>();
    private List<String> footer ;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<List<String>> getContent() {
        return content;
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    public List<String> getFooter() {
        return footer;
    }

    public void setFooter(List<String> footer) {
        this.footer = footer;
    }
}
