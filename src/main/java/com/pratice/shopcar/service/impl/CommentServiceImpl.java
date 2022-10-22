package com.pratice.shopcar.service.impl;

import com.pratice.shopcar.mappers.CommentMapper;
import com.pratice.shopcar.pojo.Comment;
import com.pratice.shopcar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getAllCommentByGid(Integer gid) {
        List<Comment> list = commentMapper.getAllCommentByGid(gid);
        return list;
    }
}
