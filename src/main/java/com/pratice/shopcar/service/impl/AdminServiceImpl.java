package com.pratice.shopcar.service.impl;

import com.pratice.shopcar.mappers.AdminMapper;
import com.pratice.shopcar.pojo.Admin;
import com.pratice.shopcar.pojo.User;
import com.pratice.shopcar.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(Admin user) throws Exception {
        Admin checkUser = adminMapper.findByUsername(user);
        System.out.println("checkUser:"+checkUser);
        String pwd1=checkUser.getPassword();
        String pwd2=user.getPassword();
        if(checkUser!=null && pwd1.equals(pwd2)){
            System.out.println("test");
            return checkUser;
        }
        else {
            throw new Exception();
        }
    }
}
