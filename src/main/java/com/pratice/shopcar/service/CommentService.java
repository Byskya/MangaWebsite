package com.pratice.shopcar.service;

import com.pratice.shopcar.pojo.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);

    List<Comment> getAllCommentByGid(Integer gid);
}
