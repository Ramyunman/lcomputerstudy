package com.lcomputerstudy.example.service;

import java.util.List;

import com.lcomputerstudy.example.domain.Comment;

public interface CommentService {

	//댓글 생성
	public void createComment(Comment comment);
	
	//댓글 리스트 불러오기
	public List<Comment> selectCommentList(int bIdx);
	
	//cIdx를 cGroup에 적용시킴
	public void updateCGroup(Comment comment);
	
	//댓글 reply 등록
	public void insertCommentReply(Comment comment);
	
	//기존 댓글 reply의 Order 증가시킴
	public void updateCOrder(Comment comment);
	
	//cIdx로 Comment 객체 가져오기
	public Comment getCommentInfo(int cIdx); 
	
	//댓글 삭제
	public void deleteComment(int cIdx);
}
