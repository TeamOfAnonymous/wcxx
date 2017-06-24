package com.anonymous.service.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaWorkProgram.PWPCategory;
import com.anonymous.domain.PropagandaWorkProgram.PWPProject;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;
import com.anonymous.repository.PropagandaWorkProgram.PWPCategoryRepository;
import com.anonymous.service.inter.PropagandaWorkProgram.PWPCategoryServiceInter;
import com.anonymous.service.inter.PropagandaWorkProgram.PropagandaWorkProgramServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案分类 Service层实现
 */
@Service
public class PWPCategoryService implements PWPCategoryServiceInter {

    /**
     * 宣传工作方案分类 Repository
     */
    private PWPCategoryRepository pwpcRepository ;

    public PWPCategoryService(
            @Autowired PWPCategoryRepository pwpcRepository
    ){
        this.pwpcRepository = pwpcRepository ;
    }

    /**
     * 查找所有
     * @return
     */
    @Override
    public List<PWPCategory> findAll(){
        return pwpcRepository.findAll();
    }

    /**
     * 添加 宣传工作方案分类
     * @param pwpc
     * @return
     */
    @Override
    public PWPCategory add(PWPCategory pwpc) {
        return pwpcRepository.save(pwpc);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean delete(String[] ids) {
        // 遍历 id集合 一个一个删除
        for( String id : ids ){
            if( ! delete( id ) ){
                throw new RuntimeException("删除 宣传工作方案分类 出现异常 ");
            }
        }
        return true;
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    private boolean delete(String id) {
        pwpcRepository.delete( id );
        return  pwpcRepository.findOne( id ) == null ? true : false ;
    }
}