package com.lcomputerstudy.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.AuthService;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.UserService;

@org.springframework.stereotype.Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userservice;	//로직 구현
	
//	@Autowired
//	AuthService authservice;
	
	@Autowired
	PasswordEncoder encoder;
	
		
	@RequestMapping("/")
	public String home(Model model) {
		
		logger.debug("debug");
		logger.info("info");
		logger.info("error");
		return "/user/index";
	}
	
	@RequestMapping("/beforeSignUp")		// 회원가입 등록창
	public String beforeSignup() {
		return "/user/signup";
	}
	
	@RequestMapping("/signup")		// 회원가입
	public String signup(User user,
			@RequestParam("tel1") String tel1, @RequestParam("tel2") String tel2, @RequestParam("tel3") String tel3) {
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
	public String login(Model model) {		
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
	public String userList(Pagination pagination, Model model, Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		
		int totalUserCount = userservice.countUser();
		pagination.setAmount(totalUserCount);
		pagination.init();
		
		List<User> userList = userservice.selectUserList(pagination);
		
		// User 객체에 Pagination 정보 설정
	    for (User user : userList) {
	        user.setCurrentPage(pagination.getPage());
	        user.setPageSize(Pagination.perPage);
	    }

		model.addAttribute("userList", userList);
		model.addAttribute("pagination", pagination);
		
		if (authentication != null && authentication.getAuthorities()
				.stream()
		        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
		        model.addAttribute("isAdmin", true);
		    }
		
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
		User beforeUser = userservice.beforeUserUpdate(uIdx);
		beforeUser.setuTelArr(beforeUser.getuTel().split("-"));	// uTel을 "-"로 쪼개어 uTelArr에 할당
		model.addAttribute("user", beforeUser);
		return "/user/update";
	}
	
	@RequestMapping("/user-update")		//user update 추가
	public String userUpdate(User user, Model model, 
			@RequestParam("tel1") String tel1, @RequestParam("tel2") String tel2, @RequestParam("tel3") String tel3) {
		
		//전화번호 세팅
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		user.setuTel(tel);
		
		userservice.updateUser(user);
		return "/user/update_result";
	}
	
	@PostMapping("/user-addRoleAdmin")			//ROLE_ADMIN 추가
	public String addRoleAdmin(@RequestParam String username) {
		User user = userservice.getUserByUsername(username);
		userservice.addRoleAdmin(user);
		return "/user/list";
		
	}

	@PostMapping("/user-removeRoleAdmin")		//ROLE_ADMIN 삭제
	public String removeRoleAdmin(@RequestParam String username) {
		User user = userservice.getUserByUsername(username);
		userservice.removeRoleAdmin(user);
		return "/user/list";
		
	}
	
}
