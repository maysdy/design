package com.service;

import com.domain.Express;

public interface ExpressService {

    String getRandomExpressId();

    int createExpress(Express express);
}
