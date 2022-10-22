package com.pratice.shopcar.controller;

import com.pratice.shopcar.pojo.Comment;
import com.pratice.shopcar.pojo.User;
import com.pratice.shopcar.service.CommentService;
import com.pratice.shopcar.service.UserService;
import com.pratice.shopcar.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @RequestMapping("/users/comment/{id}")
    public ResponseResult<User> sendComment(@PathVariable("id")Integer gid, String content, HttpSession session) throws Exception {
        System.out.println("******************评论测试："+content);
        System.out.println("获取作品id:"+gid);
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String username = (String) session.getAttribute("username");
        System.out.println("用户名："+username);
        System.out.println("用户id："+uid);
        User user = userService.getByUid(uid);
        Comment comment = new Comment(uid,gid,content,username,user.getAvatar());
        comment.setCreatedTime(new Date());
        comment.setCreatedUser(username);
        comment.setModifiedTime(new Date());
        comment.setModifiedUser(username);
        System.out.println("************************构造器生成对象："+comment);
        commentService.saveComment(comment);
        ResponseResult<User> rr = new ResponseResult<User>();
        rr.setState(200);
        rr.setData(user);
        return rr;
    }
    @RequestMapping("/get/all/comment/{gid}")
    public ResponseResult<List<Comment>> getAllCommentByGid(@PathVariable("gid")Integer gid,HttpSession session) throws Exception {
        ResponseResult<List<Comment>> rr = new ResponseResult<List<Comment>>();
        List<Comment> data = commentService.getAllCommentByGid(gid);
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        User user = userService.getByUid(uid);
        rr.setMessage(user.getAvatar());
        System.out.println("++++++++获取全部评论："+data);
        rr.setData(data);
        rr.setState(200);
        return rr;
    }
}
