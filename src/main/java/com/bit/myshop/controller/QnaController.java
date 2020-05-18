package com.bit.myshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.myshop.service.QnaService;
import com.bit.myshop.vo.QnaVo;
import com.bit.myshop.vo.UserVo;
import com.bit.security.Auth;
import com.bit.security.AuthUser;
import com.bit.web.util.WebUtil;

@Controller
@RequestMapping("/qna")
public class QnaController {
	@Autowired
	QnaService qnaService;
	
	@RequestMapping("")
	public String index(
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model
			) {
		Map<String, Object> map = qnaService.getMessageList(keyword);
		model.addAttribute("map", map);
		
		return "qna/index";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "qna/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			@AuthUser UserVo authUser, @ModelAttribute QnaVo vo,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword
			) {
		vo.setUserName(authUser.getName());
		vo.setUserNo(authUser.getNo());
		qnaService.writeMessage(vo);
		
		return "redirect:/qna" + "?kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}
	
	@RequestMapping("/view")
	public String view(
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model) {
		QnaVo qnaVo = qnaService.getMessage(no);
		
		model.addAttribute("qnaVo", qnaVo);
		model.addAttribute("keyword", keyword);
		
		return "qna/view";
	}
	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model
			) {
		QnaVo qnaVo = qnaService.getMessage(no);
		
		model.addAttribute("qnaVo", qnaVo);
		model.addAttribute("keyword", keyword);
		
		return "qna/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(
			@AuthUser UserVo authUser,
			@ModelAttribute QnaVo qnaVo,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword
			) {
		qnaVo.setUserNo(authUser.getNo());
		qnaService.updateMessage(qnaVo);
		
		return "redirect:/qna/view"
			 + "?no=" + qnaVo.getNo()
			 + "&chk=" + qnaVo.getChk()
			 + "&kwd=" + WebUtil.encodeURL(keyword, "UTF-8");
	}
	
	@Auth
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(
			@ModelAttribute QnaVo qnaVo,
			@RequestParam(value="no", required=true, defaultValue="0") Long no,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword
			) {
		qnaService.delete(no);
		
		return "redirect:/qna";
	}
}
