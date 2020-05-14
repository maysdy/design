package com.service.impl;


import com.dao.PlanDao;
import com.domain.Plan;
import com.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanDao planDao;


    @Override
    public String getRandomPlanId(){
        synchronized (this){
            while (true){
                String planId = UUID.randomUUID().toString();
                //判断是否存在 ID 重复
                if(planDao.findOneById(planId)== null){
                    return planId;
                }
            }
        }
    }

    @Override
    public int createPlan(Plan plan){
        return planDao.createPlan(plan);
    }



    @Override
    public Plan getPlanByGoodsId(String goodsId){
        return planDao.findOneByGoodsId(goodsId);
    }

    @Override
    public int changePlanByGoodsId(String goodsId, Date startdate,Date enddate,int number,String land,String area){
        return planDao.changePlanByGoodsId(goodsId, startdate, enddate, number, land, area);
    }


}
