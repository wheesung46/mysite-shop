package com.bit.myshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.myshop.repository.UserDao;
import com.bit.myshop.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public void join(UserVo vo) {
		userDao.insert(vo);
	}
	
	// 이메일 존재여부 검증
	public boolean emailExists(String email) {
		return (userDao.get(email) != null);
	}
	
	// 로그인
	public UserVo login(String email, String password) {
		UserVo userVo = null;
		userVo = userDao.get(email, password);
		return userVo;
	}
	
	// 번호로 유저정보 가져오기
	public UserVo getUser(Long no) {
		return userDao.get(no);
	}
	
	// 유저 정보 수정
	public void updateUser(UserVo vo) {
		userDao.updateUser(vo);
	}
}
