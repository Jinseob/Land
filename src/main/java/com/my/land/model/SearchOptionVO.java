package com.my.land.model;

public class SearchOptionVO{
	private String searchText1;	// 시군구
	private String searchText2;	// 단지명
	private String searchText3;	// 전용면적
	private String searchText4;	// 기간
	public String getSearchText1() {
		return searchText1 == null ? "" : searchText1;
	}
	public void setSearchText1(String searchText1) {
		this.searchText1 = searchText1;
	}
	public String getSearchText2() {
		return searchText2 == null ? "" : searchText2;
	}
	public void setSearchText2(String searchText2) {
		this.searchText2 = searchText2;
	}
	public String getSearchText3() {
		return searchText3 == null ? "" : searchText3;
	}
	public void setSearchText3(String searchText3) {
		this.searchText3 = searchText3;
	}
	public String getSearchText4() {
		return searchText4 == null ? "" : searchText4;
	}
	public void setSearchText4(String searchText4) {
		this.searchText4 = searchText4;
	}
}
