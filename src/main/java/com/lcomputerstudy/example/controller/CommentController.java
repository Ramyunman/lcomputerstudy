package com.lcomputerstudy.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Comment;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.CommentService;
import com.lcomputerstudy.example.service.UserService;

@Controller
public class CommentController {
	
	@Autowired 
	BoardService boardservice;
	
	@Autowired 
	UserService userservice;
	
	@Autowired
	CommentService commentservice;
	
	List<Comment> commentList = null;
	
	@RequestMapping("/comment-signup")
	public String createComment(Comment comment, Model model) {
		// 현재 로그인한 유저의 정보를 가져와서 comment 객체에 추가
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			User loginUser = (User) auth.getPrincipal();
			model.addAttribute("loginUser", loginUser);
			model.addAttribute("isAdmin", loginUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
		}
	    String username = auth.getName();
	    // 사용자의 User 객체를 가져옴
	    User user = userservice.getUserByUsername(username);
		// Board 객체에 사용자의 User 객체를 설정함
	    comment.setUser(user);
	    // 댓글 생성
		commentservice.createComment(comment);
		// 댓글 목록 조회 및 모델에 추가
		commentList = commentservice.selectCommentList(comment.getbIdx());
		model.addAttribute("commentList", commentList);
		return "/board/c_list";
	}
	
	@RequestMapping("/comment-reply")
	public String commentCreateReply(Comment comment, Model model) {
		// 현재 로그인한 유저의 정보를 가져와서 comment 객체에 추가
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			User loginUser = (User) auth.getPrincipal();
			model.addAttribute("loginUser", loginUser);
			model.addAttribute("isAdmin", loginUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
		}
	    String username = auth.getName();
	    // 사용자의 User 객체를 가져옴
	    User user = userservice.getUserByUsername(username);
		// Board 객체에 사용자의 User 객체를 설정함
	    comment.setUser(user);
	    // 대댓글 생성
	    commentservice.updateCOrder(comment);
	    commentservice.insertCommentReply(comment);
	    // 댓글 목록 조회 및 모델에 추가
	 	commentList = commentservice.selectCommentList(comment.getbIdx());
	 	model.addAttribute("commentList", commentList);
		return "/board/c_list";
	}
	
	@RequestMapping("/comment-delete")
	public String commentDelete(@RequestParam("cIdx") int cIdx, Comment comment, Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("isAdmin", loginUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
			}
		commentservice.deleteComment(cIdx);	
		// 댓글 목록 조회 및 모델에 추가
		commentList = commentservice.selectCommentList(comment.getbIdx());
		model.addAttribute("commentList", commentList);
		return "/board/c_list";
	}
	
	@RequestMapping("/comment-update")
	public String commentUpdate(@RequestParam("cIdx") int cIdx, Comment comment, Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				model.addAttribute("loginUser", loginUser);
				model.addAttribute("isAdmin", loginUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
			}
		commentservice.updateComment(comment);
		model.addAttribute("afterComment",comment);
		// 댓글 목록 조회 및 모델에 추가
		commentList = commentservice.selectCommentList(comment.getbIdx());
		model.addAttribute("commentList", commentList);
		return "/board/c_list";
		
	}
	
}