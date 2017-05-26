package com.my.land.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.land.dao.CmmnDao;
import com.my.land.model.SearchOptionVO;
import com.my.land.model.TBL01VO;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "main.do")
	public String main(Locale locale, ModelMap model, RedirectAttributes redirectAttr,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		SearchOptionVO searchOptionVO = new SearchOptionVO();
		@SuppressWarnings("unchecked")
		List<TBL01VO> list = (List<TBL01VO>) dao.selectList("common.selectLand", searchOptionVO);
		model.addAttribute("list", list);
		
		return "main";
	}
}
