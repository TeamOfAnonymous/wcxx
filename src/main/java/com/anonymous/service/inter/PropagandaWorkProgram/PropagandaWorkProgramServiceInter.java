package com.anonymous.service.inter.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案 Service层接口
 */
public interface PropagandaWorkProgramServiceInter {
    PropagandaWorkProgram add(PropagandaWorkProgram pwp) ;
    boolean delete(String[] ids) ;
}
