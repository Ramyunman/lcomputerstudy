package com.lcomputerstudy.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.board;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.boardService;

@org.springframework.stereotype.Controller
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService boardservice;
	@Autowired PasswordEncoder encoder;
	
/*	
	@RequestMapping("/")
	public String home(Model model) {
		
		logger.debug("debug");
		logger.info("info");
		logger.info("error");
		return "/board/index";
	}	
*/	
	@RequestMapping("/board-beforeSignUp")
	public String beforeSignup() {
		return "/board/signup";
	}
	
	@RequestMapping("/board-signup")
	public String signup(Board board) {
						
		//유저 데이터 세팅
		board.setPassword(encodedPassword);
		board.setAccountNonExpired(true);
		board.setEnabled(true);
		board.setAccountNonLocked(true);
		board.setCredentialsNonExpired(true);
		board.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_board", "ROLE_ADMIN"));
		
		//유저 생성
		boardservice.createboard(board);
		//유저 권한 생성
		boardservice.createAuthorities(board);
		
		return "/board/login";
	}
	
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/board/login";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/admin")
	public String admin(Model model) {
		return "/board/admin";
	}
		
	@Secured({"ROLE_board"})
	@RequestMapping(value="/board/info")
	public String boardInfo(Model model) {
		return "/board/info";
	}
		
	@RequestMapping(value="/denied")
	public String denied(Model model) {
		return "/board/denied";
	}
	
	@RequestMapping("/board-list")		//board list 추가
	public String boardList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		int totalboardCount = boardservice.countboard();
		
		Pagination pagination = new Pagination();
		pagination.setPage(page);
		pagination.setAmount(totalboardCount);
		pagination.init();
		
		List<board> boardList = boardservice.selectboardList(pagination);
		
		// board 객체에 Pagination 정보 설정
	    for (board board : boardList) {
	        board.setCurrentPage(pagination.getPage());
	        board.setPageSize(Pagination.perPage);
	    }

		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		return "/board/list";
	}
	
	@RequestMapping("/board-detail/{uIdx}")		//board read 추가
	public String boardDetail(@PathVariable("uIdx") int uIdx, Model model) {
		board board = boardservice.showboardDetail(uIdx);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	@RequestMapping("/board-delete/{uIdx}")		//board delete 추가
	public String boardDelete(@PathVariable("uIdx") int uIdx, Model model) {
		boardservice.deleteboard(uIdx);
		return "/board/delete";
	}
	
	@RequestMapping("/before-board-update/{uIdx}")	// board update 전
	public String beforeboardUpdate(@PathVariable("uIdx") int uIdx, Model model) {
		board beforeboard = boardservice.beforeboardUpdate(uIdx);
		beforeboard.setuTelArr(beforeboard.getuTel().split("-"));	// uTel을 "-"로 쪼개어 uTelArr에 할당
		model.addAttribute("board", beforeboard);
		return "/board/update";
	}
	
	@RequestMapping("/board-update")		//board update 추가
	public String boardUpdate(Board board, Model model, 
			@RequestParam("tel1") String tel1, @RequestParam("tel2") String tel2, @RequestParam("tel3") String tel3) {
		
		//전화번호 세팅
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		board.setuTel(tel);
		
		boardservice.updateboard(board);
		return "/board/update_result";
	}


}
