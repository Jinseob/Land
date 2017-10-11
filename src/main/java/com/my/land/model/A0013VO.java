package com.my.land.model;

public class A0013VO {	// 월별 집계 상단
	private String type1;	// 구분
	private String size1;	// 전용면적
	private String yymm;	// 월별
	private String cnt;		// 건수
	private String amt1;	// 평균가격
	private String amt2;
	public String getType1() {
		return type1 == null ? "" : type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getSize1() {
		return size1 == null ? "" : size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
	}
	public String getYymm() {
		return yymm == null ? "" : yymm;
	}
	public void setYymm(String yymm) {
		this.yymm = yymm;
	}
	public String getCnt() {
		return cnt == null ? "" : cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getAmt1() {
		return amt1 == null ? "" : amt1;
	}
	public void setAmt1(String amt1) {
		this.amt1 = amt1;
	}
	public String getAmt2() {
		return amt2 == null ? "" : amt2;
	}
	public void setAmt2(String amt2) {
		this.amt2 = amt2;
	}
}
