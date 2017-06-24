package com.anonymous.service.PropagandaWorkProgram;

import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsContent;
import com.anonymous.domain.PropagandaMaterialsProduced.PropagandaMaterialsProduced;
import com.anonymous.domain.PropagandaWorkProgram.PWPApprovalStatus;
import com.anonymous.domain.PropagandaWorkProgram.PWPProject;
import com.anonymous.domain.PropagandaWorkProgram.PropagandaWorkProgram;
import com.anonymous.repository.PropagandaWorkProgram.PropagandaWorkProgramRepository;
import com.anonymous.service.inter.PropagandaWorkProgram.PropagandaWorkProgramServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传工作方案 Service层实现
 */
@Service
public class PropagandaWorkProgramService implements PropagandaWorkProgramServiceInter {

    /**
     * 宣传工作方案 Repository
     */
    private PropagandaWorkProgramRepository pwpRepository;

    /**
     * 宣传工作方案子项目 Service
     */
    private PWPProjectService pwppService;

    public PropagandaWorkProgramService(@Autowired PropagandaWorkProgramRepository pwpRepository ) {
        this.pwpRepository = pwpRepository ;
    }

    @Override
    public PropagandaWorkProgram add(PropagandaWorkProgram pwp) {

        pwp = pwpRepository.save( pwp );

        // 设置 审批状态
        pwp.setApprovalStatus( PWPApprovalStatus.Draft);
        // 设置 拟稿时间
        pwp.setDraftDate( LocalDateTime.now() );
        // TODO 设置 起草人
        //pwp.setDraftMan();

        return pwpRepository.save( pwp );
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
                throw new RuntimeException("删除 宣传工作方案 出现异常 ");
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
        PropagandaWorkProgram pwp = pwpRepository.findOne(id);
        if( pwp != null ){
            // 删除之前，先把其下属 宣传品内容删除
            List<PWPProject> pwpps = pwp.getPwpprojects() ;
            for( PWPProject pwpp : pwpps){
                if( ! pwppService.delete( pwpp.getId() ) ){
                    throw new RuntimeException("删除 宣传工作方案 级联删除 宣传工作方案子项目 出现异常 ");
                }
            }
            pwpRepository.delete( id );
        }
        return  pwpRepository.findOne( id ) == null ? true : false ;
    }

}