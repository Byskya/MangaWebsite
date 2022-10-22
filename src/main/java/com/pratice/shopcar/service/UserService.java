package com.pratice.shopcar.service;

import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.pojo.User;

import java.util.List;

public interface UserService {
    Integer register(User user) throws Exception;

    User login(User user) throws Exception;

    User getByUid(Integer uid) throws Exception;

    void changeInfo(User user,String username);

    void changePassword(int uid, String username, String oldPassword, String newPassword) throws Exception;

    void changeAvatar(Integer uid, String avatar) throws Exception;

    PageInfo<User> getAllUser(Integer pageNum);

    void blockUser(Integer uid);
}
