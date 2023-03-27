package com.lcomputerstudy.example.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.mapper.BoardMapper;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired 
	BoardMapper boardMapper;

	@Override		// 보드 생성
	public void createBoard(Board board) {
		boardMapper.createBoard(board);
	}

	@Override		// 보드 리스트
	public List<Board> selectBoardList(Pagination pagination) {
		return boardMapper.selectBoardList(pagination);
	}

	@Override		// 보드 디테일
	public Board showBoardDetail(int bIdx) {
		return boardMapper.showBoardDetail(bIdx);
	}

	@Override		// 보드 삭제
	public void deleteBoard(int bIdx) {
		boardMapper.deleteBoard(bIdx);
	}

	@Override		//보드 수정전
	public Board beforeBoardUpdate(int bIdx) {
		return boardMapper.beforeBoardUpdate(bIdx);
	}

	@Override		// 보드 수정
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}

	@Override		//보드 카운트
	public int countBoard() {
		return boardMapper.countBoard();
	}

	@Override
	public Board authenticate(Board board, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return board;
	}
	

}
