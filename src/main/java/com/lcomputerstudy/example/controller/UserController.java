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
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.UserService;

@org.springframework.stereotype.Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired UserService userservice;
	@Autowired BoardService boardservice;
	@Autowired PasswordEncoder encoder;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardservice.selectBoardList();
		model.addAttribute("list",list);
		logger.debug("debug");
		logger.info("info");
		logger.info("error");
		return "/index";
	}
	
	@RequestMapping("/beforeSignUp")
	public String beforeSignup() {
		return "/signup";
	}
	
	@RequestMapping("/signup")
	public String signup(User user) {
		//비밀번호 암호화
		String encodedPassword = encoder.encode(user.getPassword());
		
		//유저 데이터 세팅
		user.setPassword(encodedPassword);
		user.setAccountNonExpired(true);
		user.setEnabled(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
		
		/*GrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_USER");
		GrantedAuthority role2 = new SimpleGrantedAuthority("ROLE_ADMIN");
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(role1);
		roleList.add(role2);
		user.setAuthorities(roleList);*/
		
		//유저 생성
		userservice.createUser(user);
		//유저 권한 생성
		userservice.createAuthorities(user);
		
		return "/login";
	}
	
	@RequestMapping(value="/login")
	public String beforeLogin(Model model) {
		return "/login";
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/admin")
	public String admin(Model model) {
		return "/admin";
	}
		
	@Secured({"ROLE_USER"})
	@RequestMapping(value="/user/info")
	public String userInfo(Model model) {
			
		return "/user_info";
	}
		
	@RequestMapping(value="/denied")
	public String denied(Model model) {
		return "/denied";
	}
	
	@RequestMapping("/user/list")		//user list 추가
	public String userList(Model model) {
		List<User> userList = userservice.selectUserList();
		model.addAttribute("userList", userList);
		return "/user_list";
	}


}