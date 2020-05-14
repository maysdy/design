package com.controller;

import com.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取所有用户订单信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getOrdersAllByUserId.do",method = {RequestMethod.GET})
    public @ResponseBody List<Order> getOrdersAllByUserId(@RequestParam String userId){
        return orderService.getOrdersAllByUserId(userId);
    }

    /**
     * 创建一个订单
     * @param order
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createOrder.do",method = {RequestMethod.POST})
    public void createOrder(@RequestBody Order order, HttpServletResponse response)throws IOException {
        int count = orderService.createOrder(order);
        int count1 = orderService.changeStatusByOrderId(order.getOrdersId(), 1);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 创建一个订单
     * @param goodsId
     * @throws IOException
     */
    @RequestMapping(value = "/createOrder1.do",method = {RequestMethod.GET})

    public @ResponseBody int createOrder1(@RequestParam String goodsId){

        Order order = new Order();
        order.setUserId("a6acee32-3f45-4ea7-a482-a8c05e00a330");
        order.setGoodsId(goodsId);
        int count = orderService.createOrder(order);
        int count1 = orderService.changeStatusByOrderId(order.getOrdersId(), 1);
       return count;
    }

    /**
     * 更改订单状态为3
     * @param ordersId
//     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeOrder3.do",method = {RequestMethod.GET})
    @ResponseBody
    public int changeStatusByOrderId3(@RequestParam String ordersId){
        int count = orderService.changeStatusByOrderId(ordersId, 3);
        return count;
//        HashMap<String, Integer> map = new HashMap<>();
//        if(count == 1){
//            map.put("code",1);
//        }else{
//            map.put("code",0);
//        }
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }

    /**
     * 更改订单状态为0
     * @param ordersId
    //     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeOrder0.do",method = {RequestMethod.GET})
    @ResponseBody
    public int changeStatusByOrderId0(@RequestParam String ordersId){
        int count = orderService.changeStatusByOrderId(ordersId, 0);
        return count;
//        HashMap<String, Integer> map = new HashMap<>();
//        if(count == 1){
//            map.put("code",1);
//        }else{
//            map.put("code",0);
//        }
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
    /**
     * 更改订单状态为1
     * @param ordersId
    //     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeOrder1.do",method = {RequestMethod.GET})
    @ResponseBody
    public int changeStatusByOrderId1(@RequestParam String ordersId){
        int count = orderService.changeStatusByOrderId(ordersId, 1);
        return count;
//        HashMap<String, Integer> map = new HashMap<>();
//        if(count == 1){
//            map.put("code",1);
//        }else{
//            map.put("code",0);
//        }
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
    /**
     * 更改订单状态为2
     * @param ordersId
    //     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeOrder2.do",method = {RequestMethod.GET})
    @ResponseBody
    public int changeStatusByOrderId2(@RequestParam String ordersId){
        int count = orderService.changeStatusByOrderId(ordersId, 2);
        return count;
//        HashMap<String, Integer> map = new HashMap<>();
//        if(count == 1){
//            map.put("code",1);
//        }else{
//            map.put("code",0);
//        }
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
    /**
     * 更改订单状态为-1
     * @param ordersId
    //     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/changeOrder11.do",method = {RequestMethod.GET})
    @ResponseBody
    public int changeStatusByOrderId11(@RequestParam String ordersId){
        int count = orderService.changeStatusByOrderId(ordersId, -1);
        return count;
//        HashMap<String, Integer> map = new HashMap<>();
//        if(count == 1){
//            map.put("code",1);
//        }else{
//            map.put("code",0);
//        }
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }







    /**
     * 创建用户购物车内的所有订单
     * @param order
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createOrders.do",method = {RequestMethod.POST})
    public void createOrders(@RequestBody Order order,HttpServletResponse response)throws IOException{
        int count = orderService.createOrders(order);
        HashMap<String, Integer> map = new HashMap<>();
        if(count == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
