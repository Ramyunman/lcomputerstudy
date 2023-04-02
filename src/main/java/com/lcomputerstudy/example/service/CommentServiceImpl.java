package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lcomputerstudy.example.domain.Comment;
import com.lcomputerstudy.example.mapper.CommentMapper;

public class CommentServiceImpl implements CommentService{

	@Autowired 
	CommentMapper commentMapper;
	
	@Override		//댓글 생성
	public void createComment(Comment comment) {
		commentMapper.createComment(comment);
	}

	@Override		// 댓글 목록 보기
	public List<Comment> selectCommentList(int bIdx) {
		return commentMapper.selectCommentList(bIdx);
	}

}
