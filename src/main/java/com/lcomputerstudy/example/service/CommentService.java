package com.lcomputerstudy.example.service;

import java.util.List;

import com.lcomputerstudy.example.domain.Comment;

public interface CommentService {

	//댓글 생성
	public void createComment(Comment comment);
	
	//댓글 리스트 불러오기
	public List<Comment> selectCommentList(int bIdx);
}
