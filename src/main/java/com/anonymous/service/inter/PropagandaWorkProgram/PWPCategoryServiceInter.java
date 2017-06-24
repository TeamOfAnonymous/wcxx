package com.anonymous.service.inter.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;

import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案分类 Service层接口
 */
public interface PWPCategoryServiceInter {
    PWPCategory add(PWPCategory pwpc) ;
    boolean delete(String[] ids) ;
    List<PWPCategory> findAll() ;
}
