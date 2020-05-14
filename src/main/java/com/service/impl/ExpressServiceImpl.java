package com.service.impl;

import com.dao.ExpressDao;
import com.domain.Express;
import com.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ExpressServiceImpl implements ExpressService {
    @Autowired
    private ExpressDao expressDao;

    @Override
    public String getRandomExpressId(){
        synchronized (this){
            while (true){
                String expressId = UUID.randomUUID().toString();
                //判断是否存在 ID 重复
                if(expressDao.findOneById(expressId)== null){
                    return expressId;
                }
            }
        }
    }

    @Override
    public int createExpress(Express express){
  return expressDao.createExpress(express);
    }


}
