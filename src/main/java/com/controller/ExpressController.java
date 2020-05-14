package com.controller;


import com.domain.Express;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/express")

public class ExpressController {
    @Autowired
    private ExpressService expressService;

    /**
     * 创建一个计划
     * @param express
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createExpress.do",method = {RequestMethod.POST})
    public void createExpress(@RequestBody Express express, HttpServletResponse response)throws IOException {
        int count = expressService.createExpress(express);
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
    @RequestMapping(value = "/getRandomExpressId.do",method = {RequestMethod.GET})
    public void getRandomExpressId(HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("expressId",expressService.getRandomExpressId());
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
