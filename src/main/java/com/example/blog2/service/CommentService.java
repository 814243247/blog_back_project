package com.example.blog2.service;

import com.example.blog2.po.Comment;

import java.util.List;



public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    List<Comment> listComment();

    List<String> CommentCountByMonth();

    Comment getCommentById(Long id);

    void deleteComment(Long id);
}
