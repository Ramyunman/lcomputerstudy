package com.lcomputerstudy.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

@Mapper
public interface AuthMapper {
	
	//User 권한 불러오기
	public List<GrantedAuthority> loadUserAuthorities(String username);
	
	//RoleAdmin 추가
	public void addRoleAdmin(String username);
		
	//RoleAdmin 삭제
	public void removeRoleAdmin(String username);
}
