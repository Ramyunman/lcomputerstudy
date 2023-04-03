package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Comment;
import com.lcomputerstudy.example.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired 
	CommentMapper commentMapper;
	
	@Override		//댓글 생성
	public void createComment(Comment comment) {
		commentMapper.createComment(comment);
		commentMapper.updateCGroup(comment);
	}

	@Override		// 댓글 목록 보기
	public List<Comment> selectCommentList(int bIdx) {
		return commentMapper.selectCommentList(bIdx);
	}

	@Override		//cIdx를 cGroup에 적용시킴
	public void updateCGroup(Comment comment) {
		commentMapper.updateCGroup(comment);	
	}

	@Override		//댓글 reply 등록
	public void insertCommentReply(Comment comment) {
		commentMapper.insertCommentReply(comment);
				
	}

	@Override		//기존 댓글 reply의 Order 증가시킴
	public void updateCOrder(Comment comment) {
		commentMapper.updateCOrder(comment);
		
	}

}
