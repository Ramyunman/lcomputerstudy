package com.lcomputerstudy.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.mapper.BoardMapper;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired 
	BoardMapper boardMapper;

	@Override
	public void createBoard(Board board) {
		boardMapper.createBoard(board);
	}

	@Override
	public void createAuthorities(Board board) {
		boardMapper.createAuthority(board);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = boardMapper.readAuthorities(username);
		return authorities;
	}

	@Override
	public List<Board> selectBoardList(Pagination pagination) {
		return boardMapper.selectBoardList(pagination);
	}

	@Override
	public Board showBoardDetail(int bIdx) {
		return boardMapper.showBoardDetail(bIdx);
	}

	@Override
	public void deleteBoard(int bIdx) {
		boardMapper.deleteBoard(bIdx);
	}

	@Override
	public Board beforeBoardUpdate(int bIdx) {
		return boardMapper.beforeBoardUpdate(bIdx);
	}

	@Override
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public int countBoard() {
		return boardMapper.countBoard();
	}
	

}
