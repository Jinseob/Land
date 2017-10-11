package com.my.land.model;

public class A0012VO {	// 전체 집계 하단
	private String size1;		// 전용면적
	private String amt1;		// 매매평균가
	private String amt2;		// 전세평균가
	private String diff1;		// 매매 - 전세
	private String amt3;		// 월세보증평균가
	private String amt4;		// 월세평균
	private String mmcal;		// 월세환산가
	private String yycal;		// 1년환산월세
	private String percal;		// 수익률(%)
	private String m2amt;		// m2 당매매가
	
	public String getSize1() {
		return size1 == null ? "" : size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
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
	public String getAmt3() {
		return amt3 == null ? "" : amt3;
	}
	public void setAmt3(String amt3) {
		this.amt3 = amt3;
	}
	public String getAmt4() {
		return amt4 == null ? "" : amt4;
	}
	public void setAmt4(String amt4) {
		this.amt4 = amt4;
	}
	public String getDiff1() {
		return diff1 == null ? "" : diff1;
	}
	public void setDiff1(String diff1) {
		this.diff1 = diff1;
	}
	public String getMmcal() {
		return mmcal == null ? "" : mmcal;
	}
	public void setMmcal(String mmcal) {
		this.mmcal = mmcal;
	}
	public String getYycal() {
		return yycal == null ? "" : yycal;
	}
	public void setYycal(String yycal) {
		this.yycal = yycal;
	}
	public String getPercal() {
		return percal == null ? "" : percal;
	}
	public void setPercal(String percal) {
		this.percal = percal;
	}
	public String getM2amt() {
		return m2amt == null ? "" : m2amt;
	}
	public void setM2amt(String m2amt) {
		this.m2amt = m2amt;
	}
}
