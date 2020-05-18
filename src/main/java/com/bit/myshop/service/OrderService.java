package com.bit.myshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.myshop.repository.OrderDao;
import com.bit.myshop.vo.OrderVo;

@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	public Map<String, Object> getAllList(){
		List<OrderVo> list = orderDao.getAllOrderList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		return map;
	}
	
	public Map<String, Object> getUserOrderList(Long no){
		List<OrderVo> list = orderDao.getOrderList(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		return map;
	}
	
	public boolean order(OrderVo orderVo) {
		return orderDao.insert(orderVo) == 1;
	}
}
