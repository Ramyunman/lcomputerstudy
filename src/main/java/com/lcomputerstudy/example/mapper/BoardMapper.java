package com.lcomputerstudy.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;

@Mapper
public interface BoardMapper {
			
	//권한 읽기
	public List<GrantedAuthority> readAuthorities(String username);
		
	//권한 생성
	public void createAuthority(Board board);
	
	//유저 생성
	public void createBoard(Board board);
		
	//유저 리스트 불러오기
	public List<Board> selectBoardList(Pagination pagination);
		
	//유저 상세정보 보기
	public Board showBoardDetail(int bIdx);
		
	//유저 삭제
	public void deleteBoard(int bIdx);
		
	//유저 업데이트 전
	public Board beforeBoardUpdate(int bIdx);
		
	//유저 업데이트
	public void updateBoard(Board board);
		
	//유저수 카운트
	public int countBoard();
	
}
