package com.pratice.shopcar.controller;

import com.pratice.shopcar.pojo.User;
import com.pratice.shopcar.service.UserService;
import com.pratice.shopcar.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseResult<Void> register(User user){
        System.out.println("注册*********************************");
        System.out.println(user);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        try {
            userService.register(user);
            rr.setState(200);
        } catch (Exception e) {
            rr.setState(100);
            rr.setMessage("注册失败");
            e.printStackTrace();
        }
        return rr;
    }
    @RequestMapping("/login")
    public ResponseResult<User> login(User user,HttpSession session){
        System.out.println("登录********************************");
        System.out.println(user);
        ResponseResult<User> rr = new ResponseResult<User>();
        try {
            User loginUser = userService.login(user);
            session.setAttribute("username",loginUser.getUsername());
            session.setAttribute("uid",loginUser.getUid());
            int judge = loginUser.getIsDelete();
            System.out.println("judge:"+judge);
            if(judge==1){
                rr.setState(100);
                rr.setMessage("登录失败,用户已被拉黑");
            }
            else{
                rr.setData(loginUser);
                rr.setMessage("登录成功");
                rr.setState(200);
            }
        } catch (Exception e) {
            rr.setState(100);
            rr.setMessage("登录失败，密码或账号错误");
            e.printStackTrace();
        }
        return rr;
    }
    @RequestMapping("/users/info")
    public ResponseResult<User> displayInfo(HttpSession session){
        System.out.println("displayinfo");
        // 获取uid
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        ResponseResult<User> rr = new ResponseResult<User>();
        // 查询用户数据
        User data = null;
        try {
            data = userService.getByUid(uid);
            System.out.println("*****************************"+data);
            rr.setState(200);
            rr.setData(data);// 返回

        } catch (Exception e) {
            rr.setState(100);
            e.printStackTrace();
        }
        return rr;
    }
    @RequestMapping("/users/change_info")
    public ResponseResult<Void> changeInfo(User user, HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String username = (String) session.getAttribute("username");
        System.out.println("修改用户的用户id"+session.getAttribute("uid"));
        user.setUid(uid);
        System.out.println("修改的用户的信息："+user);
        // 执行修改个人资料
        userService.changeInfo(user,username);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setState(200);
        return rr;
    }
    @RequestMapping("/users/change_password")
    public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword, HttpSession session) throws Exception {
        System.out.println("接收到修改密码请求");
        //根据session获取用户id
        int uid = Integer.valueOf(session.getAttribute("uid").toString());
        //根据uid查询用户名名称
        String username = session.getAttribute("username").toString();
        // 执行修改密码：
        userService.changePassword(uid, username, oldPassword, newPassword);
        ResponseResult<Void> rr= new ResponseResult<Void>();
        rr.setState(100);//成功
        // 返回结果
        return rr;
    }
    @RequestMapping("/test")
    public String test(){
        System.out.println("thymeleaf test**********************");
        return "test1";
    }
    //上传图片
    /** * 确定上传文件的名称 */
    private static final String UPLOAD_DIR = "upload";
    /** * 确定上传文件的最大大小 */
    private static final long UPLOAD_MAX_SIZE = 1 * 1024 * 1024;
    /** * 确定允许上传的类型的列表 */
    private static final List<String> UPLOAD_CONTENT_TYPES=
            new ArrayList<>();
    static {
        UPLOAD_CONTENT_TYPES.add("image/jpeg");
        UPLOAD_CONTENT_TYPES.add("image/png");
        UPLOAD_CONTENT_TYPES.add("image/gif");
        UPLOAD_CONTENT_TYPES.add("image/bmp");
    }
    @PostMapping("/users/change_avatar")
    public ResponseResult<String> changeAvatar(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("上传头像文件********************************"+file);
        System.out.println("上传头像文件********************************");
        // 检查文件是否为空
        if (file.isEmpty()) {// 为空：抛出异常：
            throw new Exception();
        }// 检查文件大小
        if (file.getSize() > UPLOAD_MAX_SIZE) {
            // 超出范围
            throw new Exception();
        }
        // 检查文件类型
        String contentType = file.getContentType();
        if (!UPLOAD_CONTENT_TYPES.contains(contentType)) {
            // 类型不符(contains()为false)：抛出异常：
            throw new Exception();
        }
        // 确定文件夹路径：
        request.getServletContext().getRealPath(UPLOAD_DIR);
        String parentPath = request.getServletContext().
                getRealPath(UPLOAD_DIR);
        System.err.print(parentPath);
        // 创建上传文件夹的File对象
        File parent = new File(parentPath);
        // 检查文件夹是否存在，如果不存在，则创建
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 获取原文件名：
        String originalFilename= file.getOriginalFilename();
        // 从原文件名中得到扩展名
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }

        // 确定文件名：uuid/nanoTime/...
        String filename = System.nanoTime() + suffix;
        // 创建dest对象：
        File dest = new File(parent, filename);
        try {
            file.transferTo(dest);
        }
        catch (Exception e) {
            throw new Exception();
        }
        // 获取uid：
        Integer uid =Integer.valueOf(request.getSession().getAttribute("uid").toString());
        System.out.println("上传头像*******************"+uid);
        // 生成avatar：/UPLOAD_DIR/文件名.扩展名
        String avatar = "/" + UPLOAD_DIR + "/" + filename;
        // 执行更新：
        userService.changeAvatar(uid, avatar);
        userService.changeAvatar(uid, avatar);
        ResponseResult<String> rr= new ResponseResult<String>();
        rr.setState(200);
        rr.setData(avatar);// 返回成功
        return rr;
    }
}
