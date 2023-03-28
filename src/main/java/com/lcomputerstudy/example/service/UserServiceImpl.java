package com.lcomputerstudy.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.User;
import com.lcomputerstudy.example.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.readUser(username);
		user.setAuthorities(getAuthorities(username));
		
		return user;
	}

	@Override		// 유저 읽기
	public User readUser(String username) {
		return userMapper.readUser(username);
	}

	@Override		// 유저 가입
	public void createUser(User user) {
		userMapper.createUser(user);
		
	}

	@Override
	public void createAuthorities(User user) {
		userMapper.createAuthority(user);
		
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authorities = userMapper.readAuthorities(username);
		return authorities;
	}

	@Override		// 유저 리스트 불러오기
	public List<User> selectUserList(Pagination pagination) {
		return userMapper.selectUserList(pagination);
	}
	
	@Override		// 유저 상세정보 보기
	public User showUserDetail(int uIdx) {
		return userMapper.showUserDetail(uIdx);
	}

	@Override		// 유저 삭제
	public void deleteUser(int uIdx) {
		userMapper.deleteUser(uIdx);	
	}
	
	@Override		//유저 업데이트 전
	public User beforeUserUpdate(int uIdx) {
		return userMapper.beforeUserUpdate(uIdx);
	}
	
	@Override		// 유저 업데이트
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override		// 유저수 카운트
	public int countUser() {
		return userMapper.countUser();
	}
	
}
