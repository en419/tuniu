package com.tuniu.service.impl;

import com.tuniu.Pojo.User;
import com.tuniu.dao.impl.userDaoImpl;
import com.tuniu.dao.userDao;
import com.tuniu.service.registerUserService;

public class registerUserServiceImpl  implements registerUserService {
    private userDao userDao = new userDaoImpl();

    @Override
    public boolean registeruser(User user) {
        User user1 = userDao.checkusername(user.getUsername());
        if(user1!=null){
            //不为空不能注册
            return false;
        }
        //为空可以注册
        userDao.registeruser(user);
        return true;
    }
}
