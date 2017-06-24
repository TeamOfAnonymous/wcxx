package com.anonymous.service.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPPAffairCategory;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPPAffairCategoryServiceInter;
import org.springframework.stereotype.Service;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案子项目分类 Service层实现
 */
@Service
public class PWPPAffairCategoryService implements PWPPAffairCategoryServiceInter {
    @Override
    public PWPPAffairCategory add(PWPPAffairCategory pwppa) {
        return null;
    }

    @Override
    public boolean delete(String pwpId) {
        return false;
    }
}