package com.service;

import com.domain.Plan;

import java.util.Date;

public interface PlanService {

    String getRandomPlanId();


    /**
     * 创建新种植计划
     * @param plan
     * @return
     */
    int createPlan(Plan plan);


    /**
    * 根据goodsId获取种植计划
    * @param goodsId
     * @return
     */
    Plan getPlanByGoodsId(String goodsId);

    //更改信息
    int changePlanByGoodsId(String goodsId, Date startdate,Date enddate,int number,String land,String area);

}
