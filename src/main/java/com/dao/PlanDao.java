package com.dao;

import com.domain.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PlanDao  {
    //根据goodsId查询信息
    @Select("select * from plan where goods_id = #{goodsId}")
    Plan findOneByGoodsId (String goodsId);
    //根据id查询计划
    @Select("select * from plan where plan_id = #{planId}")
    Plan findOneById(String planId);

    /*修改功能*/
    //全改
    @Update("update plan set startdate = #{startdate},enddate = #{enddate},number = #{number},land = #{land},area = #{area}   where goods_id = #{goodsId}")
    int changePlanByGoodsId(@Param("goodsId")String goodsId, @Param("startdate")Date startdate,@Param("enddate") Date enddate,@Param("number")int number,@Param("land") String land,@Param("area") String area);

    /*添加功能*/
    //创建新计划
    @Insert("insert into plan values(#{planId},#{goodsId},#{startdate},#{enddate},#{number},#{land},#{area},#{name})")
    int createPlan(Plan plan);
}
