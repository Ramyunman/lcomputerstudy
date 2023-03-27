package com.lcomputerstudy.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.service.BoardService;
import com.lcomputerstudy.example.service.UserService;

@org.springframework.stereotype.Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userservice;
	
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
	public String login(@ModelAttribute User user, HttpServletRequest request, Model model) {
		// 입력받은 id와 password로 인증 후, 해당 유저 정보를 가져온다.
		User loginUser = userservice.authenticate(user);
		
		// 유효한 사용자라면
		if(loginUser != null) {
			// session에 로그인한 사용자 정보를 저장한다.
			request.getSession().setAttribute("user", loginUser);
			//홈 화면으로 이동한다.
			return "redirect:/";
		}
		// 유효하지 않은 사용자라면
		else {
			model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/user/login";
		}
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
	public String userList(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		int totalUserCount = userservice.countUser();
		
		Pagination pagination = new Pagination();
		pagination.setPage(page);
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

	
}
