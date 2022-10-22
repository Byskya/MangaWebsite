package com.pratice.shopcar.mappers;

import com.pratice.shopcar.pojo.Admin;
import com.pratice.shopcar.pojo.User;

public interface AdminMapper {
    Admin findByUsername(Admin user);
}
