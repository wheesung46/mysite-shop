package com.bit.myshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.myshop.service.ProductService;
import com.bit.myshop.vo.ProductVo;
import com.bit.myshop.vo.UserVo;
import com.bit.security.Auth;
import com.bit.security.AuthUser;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<ProductVo> list = productService.getProductList();
		
		model.addAttribute("list", list);
		model.addAttribute("baseURL", ProductService.BASE_URL);
		
		return "product/index";
	}
	
	@Auth
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@AuthUser UserVo authUser,
			@ModelAttribute ProductVo productVo,
			@RequestParam("file") MultipartFile multipartfile
			) {
		productVo.setUserNo(authUser.getNo());
		productService.store(productVo, multipartfile);
		
		return "redirect:/product";
	}

	@Auth
	@RequestMapping(value="/delete/{no}")
	public String delete(
			@AuthUser UserVo authUser,
			@PathVariable("no") Long no
			) {
		productService.deleteProduct(authUser.getNo(), no);
		
		return "redirect:/product";
	}
	
	@Auth
	@RequestMapping(value="/updateform")
	public String updateform(
			@ModelAttribute ProductVo productVo,
			Model model
			) {
		ProductVo vo = productService.updateForm(productVo.getNo());
		model.addAttribute("baseURL", ProductService.BASE_URL);
		model.addAttribute("productVo", vo);
		
		return "product/updateform";
	}
	
	@Auth
	@RequestMapping(value="/update")
	public String update(
			@ModelAttribute ProductVo productVo
			) {
		productService.update(productVo);
		
		return "product/update";
	}
}
