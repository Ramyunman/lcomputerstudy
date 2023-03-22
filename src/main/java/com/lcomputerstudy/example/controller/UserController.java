package com.lcomputerstudy.example.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.UserService;

@org.springframework.stereotype.Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserService userservice;
//	@Autowired BoardService boardservice;
	@Autowired PasswordEncoder encoder;
	
	@RequestMapping("/")
	public String home(Model model) {
		
//		List<Board> list = boardservice.selectBoardList();
//		model.addAttribute("list",list);
		logger.debug("debug");
		logger.info("info");
		logger.info("error");
		return "/user/index";
	}
	
	@RequestMapping("/beforeSignUp")
	public String beforeSignup() {
		return "/user/signup";
	}
	
	@RequestMapping("/signup")
	public String signup(User user, @RequestParam("tel1") String tel1, @RequestParam("tel2") String tel2, @RequestParam("tel3") String tel3) {
		//비밀번호 암호화
		String encodedPassword = encoder.encode(user.getPassword());
		
		//전화번호 세팅
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		user.setuTel(tel);
		
		//유저 데이터 세팅
		user.setPassword(encodedPassword);
		user.setAccountNonExpired(true);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
		
		//유저 생성
		userservice.createUser(user);
		//유저 권한 생성
		userservice.createAuthorities(user);
		
		return "/user/login";
	}
	
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/user/login";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/admin")
	public String admin(Model model) {
		return "/user/admin";
	}
		
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/user/info")
	public String userInfo(Model model) {
		return "/user/info";
	}
		
	@RequestMapping(value="/denied")
	public String denied(Model model) {
		return "/user/denied";
	}
	
	@RequestMapping("/user-list")		//user list 추가
	public String userList(Model model) {
		List<User> userList = userservice.selectUserList();
		model.addAttribute("userList", userList);
		return "/user/list";
	}
	
	@RequestMapping("/user-detail/{uIdx}")		//user read 추가
	public String userDetail(@PathVariable("uIdx") int uIdx, Model model) {
		User user = userservice.showUserDetail(uIdx);
		model.addAttribute("user", user);
		return "/user/detail";
	}
	
	@RequestMapping("/user-delete/{uIdx}")		//user delete 추가
	public String userDelete(@PathVariable("uIdx") int uIdx, Model model) {
		userservice.deleteUser(uIdx);
		return "/user/delete";
	}
	
	@RequestMapping("/before-user-update/{uIdx}")	// user update 전
	public String beforeUserUpdate(@PathVariable("uIdx") int uIdx, Model model) {
		userservice.beforeUserUpdate(uIdx);
		return "/user/update";
	}
	
	@RequestMapping("/user-update")		//user update 추가
	public String userUpdate(@PathVariable("uIdx") int uIdx, User user, Model model) {
		userservice.updateUser(user);
		return "/user/update_result";
	}

}
