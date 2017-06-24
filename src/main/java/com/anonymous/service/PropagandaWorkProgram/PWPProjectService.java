package com.anonymous.service.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPProject;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPPAffairCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPProjectServiceInter;
import org.springframework.stereotype.Service;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目 Service层实现
 */
@Service
public class PWPProjectService implements PWPProjectServiceInter {
    @Override
    public PWPProject add(PWPProject pwpp) {
        return null;
    }

    @Override
    public boolean delete(String pwpId) {
        return false;
    }
}