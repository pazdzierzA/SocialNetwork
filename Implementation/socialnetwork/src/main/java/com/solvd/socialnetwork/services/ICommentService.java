package com.solvd.socialnetwork.services;

import java.util.List;

import com.solvd.socialnetwork.models.Comment;

public interface ICommentService extends IService<Comment>{
	List<Comment> getCommentByAuthorId(Long id);
	List<Comment> getCommentByPostId(Long id);

}