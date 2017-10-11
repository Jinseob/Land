package com.my.land.model;

public class TBL01VO {
	private String dmkey;  
	private String sgg;		// 시군구                                         
	private String bunji;  	// 번지                                          
	private String bonbun; 	// 본번                                          
	private String bubun;  	// 부번                                          
	private String bldnm;  	// 단지명/건물명                                     
	private String type2;  	// 거래구분(아파트/다세대/오피스텔)                          
	private String type1;  	// 매매/전세/월세                                    
	private String size1;  	// 전용면적(㎡)                                     
	private String size2;  	// 대지권면적(㎡)                                    
	private String contyy; 	// 계약년월                                        
	private String contdd; 	// 계약일                                         
	private Integer amt1;   	// 매매:거래금액/전월세:보증금(만원)                         
	private Integer amt2;   	// 월세(만원)                                      
	private String floor;  	// 층                                           
	private String vulidyy;	// 건축년도                                        
	private String doronm; 	// 도로명                                         
	
	public String getDmkey() {
		return dmkey;
	}
	public void setDmkey(String dmkey) {
		this.dmkey = dmkey;
	}
	public String getSgg() {
		return sgg;
	}
	public void setSgg(String sgg) {
		this.sgg = sgg;
	}
	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public String getBonbun() {
		return bonbun;
	}
	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}
	public String getBubun() {
		return bubun;
	}
	public void setBubun(String bubun) {
		this.bubun = bubun;
	}
	public String getBldnm() {
		return bldnm;
	}
	public void setBldnm(String bldnm) {
		this.bldnm = bldnm;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getSize1() {
		return size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
	}
	public String getSize2() {
		return size2;
	}
	public void setSize2(String size2) {
		this.size2 = size2;
	}
	public String getContyy() {
		return contyy;
	}
	public void setContyy(String contyy) {
		this.contyy = contyy;
	}
	public String getContdd() {
		return contdd;
	}
	public void setContdd(String contdd) {
		this.contdd = contdd;
	}
	public Integer getAmt1() {
		return amt1;
	}
	public void setAmt1(Integer amt1) {
		this.amt1 = amt1;
	}
	public Integer getAmt2() {
		return amt2;
	}
	public void setAmt2(Integer amt2) {
		this.amt2 = amt2;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getVulidyy() {
		return vulidyy;
	}
	public void setVulidyy(String vulidyy) {
		this.vulidyy = vulidyy;
	}
	public String getDoronm() {
		return doronm;
	}
	public void setDoronm(String doronm) {
		this.doronm = doronm;
	} 
}
