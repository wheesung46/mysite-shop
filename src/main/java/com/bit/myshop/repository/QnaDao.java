package com.bit.myshop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.myshop.vo.QnaVo;

@Repository
public class QnaDao {
	@Autowired
	SqlSession sqlSession;
	
	//qna 쓰기
	public int insert(QnaVo qnaVo) {
		return sqlSession.insert("qna.insert", qnaVo);
	}
	public int delete(Long no) {
		return sqlSession.delete("qna.delete", no);
	}
	
	// qna 리스트 가져오기
	public List<QnaVo> getList(String keyword){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword",  keyword);
		
		return sqlSession.selectList("qna.getList", map);
	}
	
	public QnaVo get(Long no) {
		return sqlSession.selectOne("qna.getByNo", no);
	}
	
	public int update(QnaVo qnaVo) {
		return sqlSession.update("qna.update", qnaVo);
	}
}
