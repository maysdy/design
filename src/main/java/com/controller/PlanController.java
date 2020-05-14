package com.controller;


import com.domain.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;


    /**
     * 创建一个计划
     * @param plan
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createPlan.do",method = {RequestMethod.POST})
    public void createPlan(@RequestBody Plan plan, HttpServletResponse response)throws IOException {
        int count = planService.createPlan(plan);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 获取随机不重复的计划ID
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getRandomPlanId.do",method = {RequestMethod.GET})
    public void getRandomPlanId(HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("planId",planService.getRandomPlanId());
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 保存种植计划信息
     * @param plan
     * @param response
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/savePlan.do",method = {RequestMethod.POST})
    public void savePlan(@RequestBody Plan plan,HttpServletResponse response) throws IOException {
        int code = planService.changePlanByGoodsId(plan.getGoodsId(),plan.getStartdate(),plan.getEnddate(),plan.getNumber(),plan.getLand(),plan.getArea());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("code",code);
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 根据goodsId查找plan
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "getPlan.do",method = {RequestMethod.GET})
    @ResponseBody
    public Plan findPlanByGoodsId(String goodsId){
        return planService.getPlanByGoodsId(goodsId);
    }
}
