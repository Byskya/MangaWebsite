package com.pratice.shopcar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.mappers.UserMapper;
import com.pratice.shopcar.pojo.Goods;
import com.pratice.shopcar.pojo.User;
import com.pratice.shopcar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer register(User user) throws Exception {
        String username = user.getUsername();
        User checkUser = userMapper.findByUsername(user);
        if(checkUser==null){
            user.setIsDelete(0);
            Date now = new Date();
            user.setCreatedUser(username);
            user.setCreatedTime(now);
            user.setModifiedUser(username);
            user.setModifiedTime(now);
            Integer result = userMapper.insert(user);
            return result;
        }
        else{
            throw new Exception();
        }

    }

    @Override
    public User login(User user) throws Exception {
        User checkUser = userMapper.findByUsername(user);
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

    @Override
    public User getByUid(Integer uid) throws Exception {
        User user = userMapper.findByUid(uid);
        if(user!=null){
            System.out.println("gtUid success");
        }
        else{
            throw new Exception();
        }
        return user;
    }

    @Override
    public void changeInfo(User user,String username) {
        // 向user中封装modifiedUser和modifiedTime
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        userMapper.updateUser(user)
        ;
    }

    @Override
    public void changePassword(int uid, String username, String oldPassword, String newPassword) throws Exception {
        Integer rows = null;
        if (oldPassword.equals(newPassword)){
            throw new Exception();
        }
        else{
            System.out.println("新旧密码不同，测试通过");
            String password = newPassword;
            String modifiedUser = username;
            Date modifiedTime = new Date();
            rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        }
        if (rows != 1) {
            throw new Exception();
        }
    }
    //根据uid查找数据
    private User findByUid(Integer uid) {
        return userMapper.findByUid(uid);
    }
    @Override //修改图片方法
    public void changeAvatar(Integer uid, String avatar) throws Exception {
        // 根据uid查询用户数据
        User result = findByUid(uid);
        System.out.println("要上传头像的用户信息******************"+result);
        // 判断查询结果是否为null
        if (result == null) {// 是：
            throw new Exception();
        }
        // 判断查询结果中isDelete是否为1
        System.out.println(result.getIsDelete());
        if (result.getIsDelete().equals(1)) {// 是：
            throw new Exception();
        }
        // 向user中封装modifiedUser和modifiedTime
        String modifiedUser = result.getUsername();
        Date modifiedTime = new Date();
        // 执行更新
        updateAvatar(uid, avatar, modifiedUser, modifiedTime);
    }



    private void updateAvatar(
            Integer uid, String avatar,
            String modifiedUser, Date modifiedTime)
            throws Exception {
        Integer rows = userMapper.updateAvatar(
                uid, avatar, modifiedUser,modifiedTime);
        if (rows != 1) {
            throw new Exception();}
    }

    @Override
    public PageInfo<User> getAllUser(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        List<User> list = userMapper.findAllUser();
        PageInfo<User> page = new PageInfo<User>(list,5);
        return page;
    }

    @Override
    public void blockUser(Integer uid) {
        userMapper.updateUserIsDelete(uid);
        return;
    }
}
