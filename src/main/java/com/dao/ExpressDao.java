package com.dao;


import com.domain.Express;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressDao {

    /*添加功能*/
    //添加发货信息
    @Insert("insert into express values(#{expressId)},#{ordersId},#{number})")
    int createExpress(Express express);

    @Select("select * from express where express_id = #{expressId}")
    Express findOneById(String expressId);

}
