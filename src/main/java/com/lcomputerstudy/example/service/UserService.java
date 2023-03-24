package com.lcomputerstudy.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;

public interface UserService extends UserDetailsService {
	//유저 읽기
	public User readUser(String username);
	
	//유저 생성
	public void createUser(User user);
	
	//권한 생성
	public void createAuthorities(User user);
		
	//시큐리티 권한 얻기
	Collection<GrantedAuthority> getAuthorities(String username);
	
	//유저 리스트 불러오기
	public List<User> selectUserList(Pagination pagination);
	
	//유저 상세정보 보기
	public User showUserDetail(int uIdx);
	
	//유저 삭제
	public void deleteUser(int uIdx);
	
	//유저 업데이트 전
	public User beforeUserUpdate(int uIdx);
	
	//유저 업데이트
	public void updateUser(User user);
	
	//유저수 카운트
	public int countUser();
}
