package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface AuthService {
	
	//User 권한 불러오기
	public List<GrantedAuthority> loadUserAuthorities(String username);
	
	//RoleAdmin 추가
	public void addRoleAdmin(String username);
	
	//RoleAdmin 삭제
	public void removeRoleAdmin(String username);

}
