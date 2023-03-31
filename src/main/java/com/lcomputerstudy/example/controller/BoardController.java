package com.lcomputerstudy.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.UserService;

@org.springframework.stereotype.Controller
public class BoardController {
	
	@Autowired 
	BoardService boardservice;
	
	@Autowired 
	UserService userservice;
		
	@RequestMapping("/board-beforeSignUp")
	public String beforeSignup() {
		return "/board/b_signup";
	}
	
	@RequestMapping("/board-signup")
	public String createBoard(Board board) {
		// 현재 로그인한 유저의 정보를 가져와서 board 객체에 추가
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    // 사용자의 User 객체를 가져옴
	    User user = userservice.getUserByUsername(username);
		// Board 객체에 사용자의 User 객체를 설정함
	    board.setUser(user);
	    // 보드 생성
		boardservice.createBoard(board);
		
		return "/board/b_signup_result";
	
	}
			
	@RequestMapping("/board-list")		//board list 추가
	public String boardList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
			
		int totalboardCount = boardservice.countBoard();
		
		Pagination pagination = new Pagination();
		pagination.setPage(page);
		pagination.setAmount(totalboardCount);
		pagination.init();
		
		List<Board> boardList = boardservice.selectBoardList(pagination);
		
		// board 객체에 Pagination 정보 설정
	    for (Board board : boardList) {
	        board.setCurrentPage(pagination.getPage());
	        board.setPageSize(Pagination.perPage);
	    }

		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		return "/board/b_list";
	}
	
	@RequestMapping("/board-detail/{bIdx}")		//board read 추가
	public String boardDetail(@PathVariable("bIdx") int bIdx, Model model) {
		Board board = boardservice.showBoardDetail(bIdx);
		model.addAttribute("board", board);
		return "/board/b_detail";
	}
	
	@RequestMapping("/board-delete/{bIdx}")		//board delete 추가
	public String boardDelete(@PathVariable("bIdx") int bIdx, Model model) {
		boardservice.deleteBoard(bIdx);
		return "/board/b_delete";
	}
	
	@RequestMapping("/before-board-update/{bIdx}")	// board update 전
	public String beforeboardUpdate(@PathVariable("bIdx") int bIdx, Model model) {
		Board beforeBoard = boardservice.beforeBoardUpdate(bIdx);
		model.addAttribute("board", beforeBoard);
		return "/board/b_update";
	}
	
	@RequestMapping("/board-update")		//board update 추가
	public String boardUpdate(Board board, Model model) {
		boardservice.updateBoard(board);
		model.addAttribute("afterBoard", board);
		return "/board/b_update_result";
	}

	@RequestMapping("/board-replyBeforeSignUp/{bIdx}")		//reply 등록전 폼
	public String boardReplyBeforeSignup(@PathVariable int bIdx, Model model) {
		Board parentBoard = boardservice.getBoardByBIdx(bIdx);
		int bGroup = parentBoard.getbGroup();
		int bOrder = parentBoard.getbOrder() + 1;
		int bDepth = parentBoard.getbDepth() + 1;
		model.addAttribute("bGroup", bGroup);
		model.addAttribute("bGroup", bOrder);
		model.addAttribute("bGroup", bDepth);		
		return "/board/b_reply-signup";
	}
	
	@RequestMapping("/board-replySignup")
	public String boardCreateReply(Board board) {
		// 현재 로그인한 유저의 정보를 가져와서 board 객체에 추가
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    // 사용자의 User 객체를 가져옴
	    User user = userservice.getUserByUsername(username);
		// Board 객체에 사용자의 User 객체를 설정함
	    board.setUser(user);
	    
	    // 원본 글의 bGroup, bOrder, bDepth 값을 가져옴
	    Board originalBoard = boardservice.getBoardByBIdx(board.getbGroup());
	    int originalBGroup = originalBoard.getbGroup();
	    int originalBOrder = originalBoard.getbOrder();
	    int originalBDepth = originalBoard.getbDepth();
	    
	    // 새로 작성한 답글의 bGroup, bOrder, bDepth 값을 계산하여 설정함
	    board.setbGroup(originalBGroup);
	    board.setbDepth(originalBDepth + 1);
	    boardservice.updateBOrder(originalBGroup, originalBOrder);
	    board.setbOrder(originalBOrder + 1);
	    
		// 보드 생성
	    boardservice.updateBoard(board);
		boardservice.insertBoardReply(board);
		
		return "/board/b_reply-signup_result";
	}


}

