package com.anonymous.service.inter.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目分类 Service层接口
 */
public interface PWPPAffairCategoryServiceInter {
    PWPPAffairCategory add(PWPPAffairCategory pwppa) ;
    boolean delete(String pwpId) ;
}
