package com.bit.myshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.myshop.service.OrderService;
import com.bit.myshop.vo.OrderVo;
import com.bit.myshop.vo.ProductVo;
import com.bit.myshop.vo.UserVo;
import com.bit.security.AuthUser;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("")
	public String order(Model model){
		Map<String, Object> map = orderService.getAllList();
		model.addAttribute("map", map);
		
		return "order/allorderlist";
	}
	
	@RequestMapping("/list")
	public String orderlist(
			@AuthUser UserVo authUser,
			@RequestParam(value="no", required=true, defaultValue="") Long no,
			Model model
			) {
		Map<String, Object> map = orderService.getUserOrderList(no);
		model.addAttribute("map", map);
		
		return "order/orderlist";
	}
	
	@RequestMapping("/order")
	public String order(
			@AuthUser UserVo authUser,
			@ModelAttribute ProductVo vo,
			@ModelAttribute OrderVo ovo,
			@RequestParam(value="no", required=true, defaultValue="") Long no,
			Model model
			) {
		ovo.setPname(vo.getPname());
		ovo.setPrice(vo.getPrice());
		ovo.setUserName(vo.getUserName());
		ovo.setUserNo(authUser.getNo());
		orderService.order(ovo);
		
		return "order/order";
	}
}
