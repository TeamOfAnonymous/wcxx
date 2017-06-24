package com.anonymous.service.inter.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPProject;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目 Service层接口
 */
public interface PWPProjectServiceInter {
    PWPProject add(PWPProject pwpp) ;
    boolean delete(String pwpId) ;
}
