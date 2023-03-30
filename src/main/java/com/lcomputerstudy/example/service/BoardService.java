package com.lcomputerstudy.example.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;

public interface BoardService {
		
	//유저 생성
	public void createBoard(Board board);
		
	//보드 리스트 불러오기
	public List<Board> selectBoardList(Pagination pagination);
		
	//보드 상세정보 보기
	public Board showBoardDetail(int bIdx);
		
	//보드 삭제
	public void deleteBoard(int bIdx);
		
	//보드 업데이트 전
	public Board beforeBoardUpdate(int bIdx);
		
	//보드 업데이트
	public void updateBoard(Board board);
		
	//보드수 카운트
	public int countBoard();
	
	//bGroup을 bIdx와 같게 만들기
	public void updateBGroup(int bIdx);
	
	//reply 등록
	public void insertBoardReply(Board board);
	
	//기존 reply의 순서를 증가시킴
	public Board updateBoardOrder(Board board);
	
}
