package com.pratice.shopcar.controller;

import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.pojo.Admin;
import com.pratice.shopcar.pojo.User;
import com.pratice.shopcar.service.AdminService;
import com.pratice.shopcar.service.UserService;
import com.pratice.shopcar.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public ResponseResult<Admin> login(Admin user, HttpSession session){
        System.out.println("登录********************************");
        System.out.println(user);
        ResponseResult<Admin> rr = new ResponseResult<Admin>();
        try {
            Admin loginUser = adminService.login(user);
            session.setAttribute("adminName",loginUser.getUsername());
            session.setAttribute("adminUid",loginUser.getUid());
            rr.setData(loginUser);
            rr.setMessage("登录成功");
            rr.setState(200);
        } catch (Exception e) {
            rr.setState(100);
            rr.setMessage("登录失败");
            e.printStackTrace();
        }
        return rr;
    }
    @RequestMapping("/get/alluser/{pageNum}")
    public ResponseResult<PageInfo<User>> getAllUser(@PathVariable("pageNum")Integer pageNum, Model model){
        System.out.println("发送查询所有员工请求");
        ResponseResult<PageInfo<User>> rr = new ResponseResult<PageInfo<User>>();
        PageInfo<User> data= userService.getAllUser(pageNum);
        System.out.println("************************查询所有员工信息："+data);
        rr.setData(data);
        rr.setState(200);
        model.addAttribute("prePage23",data.getPrePage());
        model.addAttribute("nextPage23",data.getNextPage());
        return rr;
    }
    @RequestMapping("/update/block/{uid}")
    public ResponseResult<Void> blockUser(@PathVariable("uid") Integer uid){
        ResponseResult<Void> rr = new ResponseResult<Void>();
        userService.blockUser(uid);
        rr.setState(200);
        return rr;
    }
}
