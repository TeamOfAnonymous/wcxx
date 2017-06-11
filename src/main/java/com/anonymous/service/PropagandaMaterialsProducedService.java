package com.anonymous.service;

import com.anonymous.domain.PropagandaMaterialsProduced.*;
import com.anonymous.domain.PropagandaMaterialsProduced.dto.PropagandaMaterialsProducedDto;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedStatisticalQuery;
import com.anonymous.domain.User;
import com.anonymous.repository.PropagandaMaterialsProduced.PropagandaMaterialsProducedRepository;
import com.anonymous.repository.PropagandaMaterialsProduced.PropagandaMaterialsProducedRepositoryCustom;
import com.anonymous.service.inter.PropagandaMaterialsProducedServiceInter;
import com.anonymous.domain.PropagandaMaterialsProduced.query.PropagandaMaterialsProducedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by huangMP on 2017/5/26.
 * decription : 宣传品制作申请 Service层实现
 */
@Service
public class PropagandaMaterialsProducedService implements PropagandaMaterialsProducedServiceInter {


    /**
     * 宣传品制作申请 dao
     */
    private PropagandaMaterialsProducedRepository propagandaMaterialsProducedRepository;

    /**
     * 宣传信息 service
     */
    private PropagandaMaterialsContentService propagandaMaterialsContentService;

    /**
     * 宣传品制作申请 Custom dao
     */
    private PropagandaMaterialsProducedRepositoryCustom propagandaMaterialsProducedRepositoryCustom ;

    public PropagandaMaterialsProducedService(@Autowired PropagandaMaterialsProducedRepository propagandaMaterialsProducedRepository ,
                                              @Autowired PropagandaMaterialsContentService propagandaMaterialsContentService ,
                                              @Autowired PropagandaMaterialsProducedRepositoryCustom propagandaMaterialsProducedRepositoryCustom){
        this.propagandaMaterialsProducedRepository = propagandaMaterialsProducedRepository;
        this.propagandaMaterialsContentService = propagandaMaterialsContentService;
        this.propagandaMaterialsProducedRepositoryCustom = propagandaMaterialsProducedRepositoryCustom ;
    }

    @Override
    public PropagandaMaterialsProduced findById(String id) {
        return propagandaMaterialsProducedRepository.findOne(id);
    }

    /**
     * 添加 宣传品制作申请 到草稿箱
     * @param pm
     * @return
     */
    @Override
    @Transactional
    public PropagandaMaterialsProduced add(PropagandaMaterialsProduced pm ) {

        // 先保存，目的是为了得到 id ，PropagandaMaterialsContent 保存时才能维护两者的关系
        pm = propagandaMaterialsProducedRepository.save(pm);

        // 初始化总费用
        float totalCost = 0;

        // 先把该申请里面的 宣传品内容 保存了
        List<PropagandaMaterialsContent> pmcs = pm.getPropagandaMaterialsContents();
        for (PropagandaMaterialsContent item : pmcs ) {
            item.setPropagandaMaterialsProduced( pm );
            item = propagandaMaterialsContentService.add(item);
            // 更新 总费用
            totalCost += item.getCost();
        }

        //TODO 给该申请添加 申请人
        //TODO 给该申请添加 负责人
        // 给该申请添加 总费用
        pm.setTotalCost(totalCost);
        // 给该申请设置 审批状态为0 : 0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档
        pm.setApprovalStatus( ApprovalStatus.Draft );
        //给该申请添加 所属宣传信息申请
//        PropagandaInformation propagandaInformation = propagandaInformationService.getById( propagandaInformationId );
//        propagandaMaterialsProduced.setPropagandaInformation(propagandaInformation);

        return propagandaMaterialsProducedRepository.save(pm);
    }

    /**
     * 更新申请
     * @param propagandaMaterialsProduced
     * @return
     */
    @Override
    @Transactional
    public PropagandaMaterialsProduced update(PropagandaMaterialsProduced propagandaMaterialsProduced) {
        return propagandaMaterialsProducedRepository.save( propagandaMaterialsProduced );
    }

    /**
     * 提交申请
     * @param id
     * @return
     */
    @Override
    @Transactional
    public PropagandaMaterialsProduced apply(String id) {
        PropagandaMaterialsProduced propagandaMaterialsProduced = propagandaMaterialsProducedRepository.findOne(id);

        // 修改 审批状态为1 : 0为草稿，1为待审批，2为审核中，3为执行中，4为已完成，5为已归档
        propagandaMaterialsProduced.setApprovalStatus( ApprovalStatus.WaitForApproval );
        // 给该申请添加 申请时间
        propagandaMaterialsProduced.setApplicationDate( LocalDateTime.now() );
        return propagandaMaterialsProducedRepository.save(propagandaMaterialsProduced);
    }

    /**
     * 业务查询 : 宣传品（资料）制作查询
     * Criteria 实现
     *  title 标题
     *  applicantName 宣传品申请人姓名
     *  productionMethod 制作方式
     *  minTotalCost 总费用最小值
     *  maxTotalCost 总费用最大值
     *  approvalStatus 状态
     * @param query
     * @return List<PropagandaMaterialsProduced>
     */
    @Override
    public Page findByQuery(PropagandaMaterialsProducedQuery query){

        // 创建分页结构
        Pageable pageable = new PageRequest( query.getPage(), query.getRows());
        // 符合条件的总条数
        long totalNum = propagandaMaterialsProducedRepositoryCustom.countByQuery(query);
        // 当前页的起始下标
        long startIndex = pageable.getOffset() ;
        // 当前页的结束下标
        long endIndex = startIndex + pageable.getPageSize() > totalNum ? totalNum : startIndex + pageable.getPageSize() ;

        // 创建 返回的dtolist List<PropagandaMaterialsProducedDto>
        List<PropagandaMaterialsProducedDto> dtos = new ArrayList<>();

        // 得到 查询得到 List<PropagandaMaterialsProduced>
        List<PropagandaMaterialsProduced> pmpList = (List) propagandaMaterialsProducedRepositoryCustom.findByQuery( query, (int) startIndex, (int) endIndex);

        // 为 每一个 PropagandaMaterialsProducedDto 设置 pmcProductionMethod
        for( PropagandaMaterialsProduced item : pmpList ){

            // 创建一个 dto
            PropagandaMaterialsProducedDto dtoItem = new PropagandaMaterialsProducedDto();

            // 得到 宣传品内容 的所有制作方式 （去重复项）
            Set pmcProductionMethodSets = new HashSet() ;
            for( PropagandaMaterialsContent itemContent : item.getPropagandaMaterialsContents() ){
                pmcProductionMethodSets.add(itemContent.getProductionMethod());
            }

            // 构造 dto 中的 pmcProductionMethod
            for( Iterator pmcProductionMethodSetsI = pmcProductionMethodSets.iterator() ;
                    pmcProductionMethodSetsI.hasNext() ; ){
                ProductionProducedMethod itemMethod = (ProductionProducedMethod) pmcProductionMethodSetsI.next();
                if( dtoItem.getPmcProductionMethod() == null || "".equals( dtoItem.getPmcProductionMethod().trim() ) ){
                    dtoItem.setPmcProductionMethod( itemMethod.getName() ) ;
                    continue;
                }
                dtoItem.setPmcProductionMethod( dtoItem.getPmcProductionMethod() + "、" + itemMethod.getName() ) ;
            }
            // 组合 PropagandaMaterialsProduced
            dtoItem.setPmp(item);
            dtos.add(dtoItem) ;
        }

        // 返回封装好的结果 Page
        return new PageImpl( dtos , pageable , totalNum ) ;
    }

    /**
     * 业务查询 : 宣传品（资料）制作查询
     * Example 实现
     *  title 标题
     *  applicantName 宣传品申请人姓名
     *  productionMethod 制作方式
     *  minTotalCost 总费用最小值
     *  maxTotalCost 总费用最大值
     *  approvalStatus 状态
     * @return
     */
    @Override
    public Page<PropagandaMaterialsProduced> findByMultiExample(PropagandaMaterialsProducedQuery query){

        // 定义排序规则
        String[] sortContent = {"applicationDate","totalCost"};
        Sort sort = new Sort( Sort.Direction.DESC, sortContent);

        List<PropagandaMaterialsProduced> list ;

        PropagandaMaterialsProduced p = query.getPropagandaMaterialsProduced() ;
        if( p != null ){
            // 定义查询规则
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains()) // 标题
                    .withMatcher("applicant.name", ExampleMatcher.GenericPropertyMatchers.contains()) // 申请人姓名
                    //.withMatcher("approvalStatus", ExampleMatcher.GenericPropertyMatchers.exact()) // 审批状态
                    //.withMatcher("propagandaMaterialsContent.productionMethod", ExampleMatcher.GenericPropertyMatchers.contains()) // 制作方式
                    .withIgnorePaths("totalCost")    // 忽略 总费用
                    .withIgnorePaths("remarks")    // 忽略 备注
                    .withIgnorePaths("propagandaMaterialsContents")    // 忽略 宣传品（资料）内容
                    .withIgnorePaths("principal")    // 忽略 负责人
                    .withIgnorePaths("applicationDate")    // 忽略 申请时间
                    .withIgnorePaths("id")    // 忽略 id
                    .withIgnoreNullValues();
            Example example = Example.of( p , matcher);
            list = propagandaMaterialsProducedRepository.findAll( example, sort);
        }else{
            list = propagandaMaterialsProducedRepository.findAll( sort);
        }

        // 去除 totalCost 不符合的数据
        List<PropagandaMaterialsProduced> listRemoveByTotalCost = new ArrayList<>();
        for(PropagandaMaterialsProduced item : list){
            // 最大值等于 0 （也就是对最大值没有限制）
            if( query.getMaxTotalCost() == 0 ){
                if( item.getTotalCost() >= query.getMinTotalCost() ){
                    listRemoveByTotalCost.add( item );
                }
            }else{
                if( item.getTotalCost() <= query.getMaxTotalCost() && item.getTotalCost() >= query.getMinTotalCost() ){
                    listRemoveByTotalCost.add( item );
                }
            }
        }
        list = listRemoveByTotalCost ;

        // 去除 productionMethods 不符合的数据
        if( query.getProductionMethod() != null ){
            List<PropagandaMaterialsProduced> listRemoveByProductionProducedMethod = new ArrayList<>();
            for(PropagandaMaterialsProduced item : list){
                // 利用 hashmap 统计 每一个宣传品制作申请 包含几种制作方式
                Set productionMethods = new HashSet<ProductionProducedMethod>();
                for(PropagandaMaterialsContent pmcItem : item.getPropagandaMaterialsContents() ){
                    productionMethods.add( pmcItem.getProductionMethod() );
                }
                if( productionMethods.contains( query.getProductionMethod() ) ){
                    listRemoveByProductionProducedMethod.add( item );
                }
            }
            list = listRemoveByProductionProducedMethod ;
        }

        // 创建分页结构
        Pageable pageable = new PageRequest( query.getPage(), query.getRows(), sort);

        // 构造准备返回给前台的 list
        List<PropagandaMaterialsProduced> resultList = new ArrayList<>();
        // 符合条件的总条数
        int totalNum = list.size();
        // 当前页的起始下标
        int startIndex = pageable.getOffset() ;
        // 当前页的结束下标
        int endIndex = startIndex + pageable.getPageSize() > totalNum ? totalNum : startIndex + pageable.getPageSize() ;
        for(int i = startIndex ; i < endIndex ; i++ ){
            resultList.add( list.get(i) );
        }

        // 返回封装好的结果
        return new PageImpl( resultList , pageable , totalNum ) ;
    }


    /**
     * 通过 开始时间 和 结束时间 ， 返回统计信息（二维表）
     * @param query
     * @return
     */
    @Override
    public List<List<String>> statisticalQuery(PropagandaMaterialsProducedStatisticalQuery query) {

        // 找出该日期下的所有 宣传品制作申请
        List<PropagandaMaterialsProduced> pmps = propagandaMaterialsProducedRepository.findByApplicationDateBetween(query.getStartTime() , query.getEndTime());

        // 利用 hashmap 将得到的 宣传品制作申请list 按申请人分组 key:User value:List<PropagandaMaterialsProduced>
        Map pmpsGroupByApplicant = new HashMap<User,List<PropagandaMaterialsProduced>>();
        for(PropagandaMaterialsProduced pItem : pmps){
            User applicant = pItem.getApplicant();
            List<PropagandaMaterialsProduced> applicantList = (List<PropagandaMaterialsProduced>) pmpsGroupByApplicant.get(applicant);
            if( applicantList == null ){
                applicantList = new ArrayList<>();
            }
            applicantList.add(pItem);
            pmpsGroupByApplicant.put(applicant,applicantList);
        }

        // 构造返回的 二维数组   -- start
        List<List<String>> resultTable = new ArrayList<>();

        // 构造 返回的二维数组的表头   -- start
        List<String> tableHeader = new ArrayList<>();
        tableHeader.add("负债人");
        for(PromotionalCategory item : PromotionalCategory.values()){
            tableHeader.add(item.getName());
        }
        resultTable.add(tableHeader);
        // 构造 返回的二维数组的表头   -- end

        // 构造返回的 二维数组的数据   -- start
        Iterator pmpsAndApplicants = pmpsGroupByApplicant.entrySet().iterator();

        // 遍历 申请者
        while( pmpsAndApplicants.hasNext() ){

            int[] everyNum = new int[ PromotionalCategory.values().length ];

            // 取出一个 Map.Entry 里面 key:User value:List<PropagandaMaterialsProduced>
            Map.Entry pmpsAndApplicant = (Map.Entry) pmpsAndApplicants.next();
            // 取出 申请者 的所有 宣传品制作申请
            List<PropagandaMaterialsProduced> pmpsOfApplicant = (List<PropagandaMaterialsProduced>) pmpsAndApplicant.getValue();

            // 遍历 宣传品制作申请
            for(PropagandaMaterialsProduced pmp : pmpsOfApplicant){
                // 遍历 宣传品内容
                for(PropagandaMaterialsContent pmc : pmp.getPropagandaMaterialsContents()){
                    everyNum[ pmc.getPromotionalCategory().ordinal() ] += 1;
                }
            }
            // 构造二维数组的 一行   -- start
            List<String> userRow = new ArrayList<>();
            // 添加 申请者姓名
            User applicant = (User) pmpsAndApplicant.getKey();
            userRow.add(applicant.getName());
            // 添加 对应的数据
            for(int current : everyNum){
                userRow.add( String.valueOf(current) );
            }

            // 构造二维数组的 一行   -- end
            resultTable.add(userRow);

        }
        // 构造返回的 二维数组的数据  -- end
        // 构造返回的 二维数组  -- end

        // 计算总计
        getSumForTable(resultTable);

        // 遍历该 二维表
        for( List<String> row : resultTable ){
            for( String column : row){
                System.out.print("\t " + column);
            }
            System.out.println();
        }

        return resultTable;
    }

    /**
     * 为一个 二维数组 List<List<String>> 添加 总计结果
     * 在右侧添加一列，在下面添加一行
     * @param table
     * @return
     */
    private List<List<String>> getSumForTable(List<List<String>> table){

        // 等一下需要在 table 最下面增加一行,创建这个list是为了方便计算每一列的 总计
        int[] helpCalculateList = new int[table.get(0).size()+1];

        // 添加总计逻辑  -- start
        for( List<String> row : table ){
            int indexOfRow = table.indexOf(row);
            // 在首行末尾添加 “总计”
            if( indexOfRow == 0 ){
                row.add("总计");
                continue;
            }
            int rowSum = 0; // 一行的总计
            for( String column : row ){
                // 每一行的 第一个 是申请人名称 ，不进行计算
                int indexOfColum = row.indexOf(column);
                if( indexOfColum == 0 ){
                    continue;
                }
                // 累加行的每一个值
                rowSum += Integer.valueOf( column );
                // 累加列的每一个值
                helpCalculateList[indexOfColum] += Integer.valueOf( column ) ;
            }
            // 在每一行的末尾添加 总计数量
            row.add(String.valueOf(rowSum));
            // 累加 最后一列的每一个值
            helpCalculateList[ row.size()-1 ] += Integer.valueOf( rowSum ) ;
        }

        // 构造最后一行 tailRow
        List<String> tailRow = new ArrayList<>();
        for( int index = 0 ; index < helpCalculateList.length ; index++ ){
            // 在行首添加 “总计”
            if( index == 0 ){
                tailRow.add("总计");
                continue;
            }
            tailRow.add( String.valueOf( helpCalculateList[index] ) );
        }

        // 给二维表添加最后一行
        table.add(tailRow);

        // 添加总计逻辑  -- end

        return table;
    }


}
