package com.lcomputerstudy.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;

@Mapper
public interface UserMapper {
	//유저 읽기
	public User readUser(String username);
	
	//유저 생성
	public void createUser(User user);
	
	//권한 읽기
	public List<GrantedAuthority> readAuthorities(String username);
	
	//권한 생성
	public void createAuthority(User user);
	
	//유저 리스트 불러오기
	public List<User> selectUserList(@Param("pagination") Pagination pagination);
	
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
