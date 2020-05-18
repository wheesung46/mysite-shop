package com.bit.myshop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.myshop.vo.OrderVo;

@Repository
public class OrderDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<OrderVo> getAllOrderList(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		return sqlSession.selectList("order.getAllOrderList", map);
	}
	
	public List<OrderVo> getOrderList(Long no){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		
		return sqlSession.selectList("order.getOrderList", map);
	}
	
	public int insert(OrderVo orderVo) {
		System.out.println("---dao---" + orderVo.getUserName());
		return sqlSession.insert("order.order", orderVo);
	}
}
