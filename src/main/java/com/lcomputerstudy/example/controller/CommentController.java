package com.lcomputerstudy.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

public class CommentController {
	
	@Autowired 
	BoardService boardservice;
	
	@Autowired 
	UserService userservice;
	
	@Autowired
	CommentService commentservice;
	
	@RequestMapping("/comment-signup")
	public String createComment(Comment comment, Model model) {
		// 현재 로그인한 유저의 정보를 가져와서 comment 객체에 추가
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    // 사용자의 User 객체를 가져옴
	    User user = userservice.getUserByUsername(username);
		// Board 객체에 사용자의 User 객체를 설정함
	    comment.setUser(user);
	    // 댓글 생성
		commentservice.createComment(comment);
		return "/board/c_list";
	}
	
}