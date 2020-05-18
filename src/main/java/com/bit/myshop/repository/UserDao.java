package com.bit.myshop.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.myshop.exception.UserDaoException;
import com.bit.myshop.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	// 사용자 등록
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
	}
	
	// 중복 이메일 체크
	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	// 인증(로그인)
	public UserVo get(String email, String password)  throws UserDaoException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlSession.selectOne("user.getByEmailAndPassword", map);
	}
	
	// 번호로 사용자 정보 가져오기
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	// 사용자 정보 수정
		public void updateUser(UserVo vo) {
			sqlSession.update("user.update",vo);
		}
}
