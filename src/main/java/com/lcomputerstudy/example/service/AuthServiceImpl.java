package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.mapper.AuthMapper;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthMapper authMapper;
	
	@Override		//User 권한 불러오기
	public List<GrantedAuthority> loadUserAuthorities(String username) {
		return authMapper.loadUserAuthorities(username);
	}
	

	@Override		//RoleAdmin 추가
	public void addRoleAdmin(String username) {
		authMapper.addRoleAdmin(username);
		
	}

	@Override		//RoleAdmin 삭제
	public void removeRoleAdmin(String username) {
		authMapper.removeRoleAdmin(username);
		
	}

	

	
	
	


}
