package com.pratice.shopcar.mappers;

import com.pratice.shopcar.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    void insertComment(Comment comment);

    List<Comment> getAllCommentByGid(Integer gid);
}
