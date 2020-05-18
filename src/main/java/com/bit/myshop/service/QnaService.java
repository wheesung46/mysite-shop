package com.bit.myshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.myshop.repository.QnaDao;
import com.bit.myshop.vo.QnaVo;

@Service
public class QnaService {
	
	@Autowired
	private QnaDao qnaDao;
	
	public Map<String, Object> getMessageList(String keyword){
		List<QnaVo> list = qnaDao.getList(keyword);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("keyword", keyword);
		
		return map;
	}
	public boolean delete(Long no) {
		return qnaDao.delete(no) == 1;
	}
	
	public boolean writeMessage(QnaVo qnaVo) {
		return qnaDao.insert(qnaVo) == 1;
	}
	
	public QnaVo getMessage(Long no) {
		QnaVo qnaVo = qnaDao.get(no);
		return qnaVo;
	}
	
	public boolean updateMessage(QnaVo qnaVo) {
		return qnaDao.update(qnaVo) == 1;
	}
}
