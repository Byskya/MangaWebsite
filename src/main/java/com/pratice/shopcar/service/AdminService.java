package com.pratice.shopcar.service;

import com.pratice.shopcar.pojo.Admin;
import com.pratice.shopcar.pojo.User;

public interface AdminService {
    Admin login(Admin user) throws Exception;
}
