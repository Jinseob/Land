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
import com.my.land.model.A0012VO;
import com.my.land.model.A0013VO;
import com.my.land.model.A0014VO;
import com.my.land.model.SearchOptionVO;

@Controller
public class A0010Controller {
	private static final Logger logger = LoggerFactory.getLogger(A0010Controller.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/A0010.do")
	public String a0010(Locale locale, ModelMap model, RedirectAttributes redirectAttr,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		SearchOptionVO searchOptionVO = new SearchOptionVO();
		
		/* 전체집계 */
		A0012VO a0012 = (A0012VO) dao.select("common.selectLandTotal", searchOptionVO);
		model.addAttribute("a0012", a0012);
		
		/* 월별집계 상단 */
		@SuppressWarnings("unchecked")
		List<A0013VO> a0013List = (List<A0013VO>) dao.selectList("common.selectLandMMHead", searchOptionVO);
		model.addAttribute("a0013List", a0013List);
		
		/* 월별집계 하단 */
		@SuppressWarnings("unchecked")
		List<A0014VO> a0014List = (List<A0014VO>) dao.selectList("common.selectLandMMTotal", searchOptionVO);
		model.addAttribute("a0014List", a0014List);
		
		return "a0010";
	}
}
