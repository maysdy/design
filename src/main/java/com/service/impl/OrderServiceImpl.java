package com.service.impl;

import com.dao.GoodsDao;
import com.dao.OrderDao;
import com.dao.ShopCarDao;
import com.domain.Goods;
import com.domain.Order;
import com.domain.tablereflect.ShopCar;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ShopCarDao shopCarDao;

    @Override
    public List<Order> getOrdersAllByUserId(String userId) {
        return orderDao.findAllByUid(userId);
    }

    @Override
    public int createOrder(Order order) {
        synchronized (this) {
            while (true) {
                String ordersId = UUID.randomUUID().toString();
                if (orderDao.findOneByOrderId(ordersId) == null) {
                    Goods goods = goodsDao.findOneById(order.getGoodsId());
                    int count = goodsDao.reducebyId(order.getGoodsId());
                    String solderId = goodsDao.selectUser(order.getGoodsId());
                    order.setName(goods.getName());
                    order.setSolderId(solderId);
                    order.setOrdersId(ordersId);
                    order.setPayId(UUID.randomUUID().toString());
                    order.setPrice(goods.getPrice());
                    order.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                    return orderDao.createOrder(order);
                }
            }
        }
    }

    @Override
    public int changeStatusByOrderId(String ordersId, int status){
        return orderDao.changeStatusByOrderId(ordersId,status);
    }

    @Override
    public int createOrders(Order order) {
        boolean flag = true;
        List<ShopCar> allByUid = shopCarDao.findAllByUid(order.getUserId());
        for(ShopCar shopCar : allByUid){
            Goods goods = goodsDao.findOneById(shopCar.getGoodsId());
            synchronized (this) {
                while (true) {
                    String ordersId = UUID.randomUUID().toString();
                    if (orderDao.findOneByOrderId(ordersId) == null) {
                        int count = goodsDao.reducebyId(order.getGoodsId());
                        String solderId = goodsDao.selectUser(goods.getGoodsId());
                        order.setSolderId(solderId);
                        order.setName(goods.getName());
                        order.setGoodsId(goods.getGoodsId());
                        order.setOrdersId(ordersId);
                        order.setPayId(UUID.randomUUID().toString());
                        order.setPrice(goods.getPrice());
                        order.setOrdersStatus(1);
                        order.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                        if(orderDao.createOrder(order) != 1) {
                            flag = false;
                        }
                        break;
                    }
                }
            }
            if(!flag){
                return 0;
            }
        }
        return 1;
    }
}
