package com.lcomputerstudy.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lcomputerstudy.example.domain.Comment;

@Mapper
public interface CommentMapper {
	
	//댓글 생성
	public void createComment(Comment comment);

	//댓글 리스트 불러오기
	public List<Comment> selectCommentList(int bIdx);
	
	//cIdx를 cGroup에 적용시킴
	public void updateCGroup(Comment comment);
	
}
