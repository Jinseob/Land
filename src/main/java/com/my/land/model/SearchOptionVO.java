package com.my.land.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SearchOptionVO extends LoginVO{
	public SearchOptionVO() throws Exception {
		//검색조건이 없을시 기본 3일로 셋팅
		Date dt = new Date();
		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
	
		setSearchTo(smt.format(dt));
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	//	        cal.add(Calendar.DATE, -3);		//3일 전
	    cal.add(Calendar.MONTH, -1);		//1달 전
	
		setSearchFrom(smt.format(cal.getTime()));
	}
	
	private String searchDiv;		// 구분자
	private String searchText;		// 검색어
	private String searchFrom;		// 시작일
	private String searchTo;		// 종료일
	private String searchYear;		// 검색년도
	private String searchOrderType;	// 주문 타입(O : 주문, M : 제조의뢰), GCM (I : Insert, U : Update)
	private String searchYYMM;		// 검색년월
	private String selectedKey;		// 키값
	private String selectedSeq;		// 순서
	private String selectedDate;	// 선택된 날짜
	private String selectedSite;	// 사업장
	private String searchItemGroup;	// 대분류
	
	// 그룹 코드
	private String searchCd;		// 그룹 코드
	
	// Paging
	private String currentPage;
	private String countPerPage;
	private String totalCount;

	public String getSearchDiv() {
		return searchDiv == null ? "" : searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchText() {
		return searchText == null ? "" : searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchFrom() {
		return searchFrom == null ? "" : searchFrom;
	}

	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}

	public String getSearchTo() {
		return searchTo == null ? "" : searchTo;
	}

	public void setSearchTo(String searchTo) {
		this.searchTo = searchTo;
	}

	public String getSearchYear() {
		return searchYear == null ? "" : searchYear;
	}

	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}

	public String getSearchCd() {
		return searchCd == null ? "" : searchCd;
	}

	public void setSearchCd(String searchCd) {
		this.searchCd = searchCd;
	}

	public String getSearchOrderType() {
		return searchOrderType == null ? "" : searchOrderType;
	}

	public void setSearchOrderType(String searchOrderType) {
		this.searchOrderType = searchOrderType;
	}

	public String getSearchYYMM() {
		return searchYYMM == null ? "" : searchYYMM;
	}

	public void setSearchYYMM(String searchYYMM) {
		this.searchYYMM = searchYYMM;
	}

	public String getSelectedKey() {
		return selectedKey == null ? "" : selectedKey;
	}

	public void setSelectedKey(String selectedKey) {
		this.selectedKey = selectedKey;
	}

	public String getSelectedDate() {
		return selectedDate == null ? "" : selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedSite() {
		return selectedSite == null ? "" : selectedSite;
	}

	public void setSelectedSite(String selectedSite) {
		this.selectedSite = selectedSite;
	}

	public String getSelectedSeq() {
		return selectedSeq == null ? "" : selectedSeq;
	}

	public void setSelectedSeq(String selectedSeq) {
		this.selectedSeq = selectedSeq;
	}

	public String getCurrentPage() {
		return currentPage == null ? "1" : currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCountPerPage() {
		return countPerPage == null ? "10" : countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getTotalCount() {
		return totalCount == null ? "" : totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getSearchItemGroup() {
		return searchItemGroup == null ? "" : searchItemGroup;
	}

	public void setSearchItemGroup(String searchItemGroup) {
		this.searchItemGroup = searchItemGroup;
	}
}
