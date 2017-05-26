package com.my.land.model;

public class LoginVO{
	
	// 사용자 정보
	private String user_id;
	private String passwd;
	private String user_nm;
	private String auth;
	private String set_item_group;
	private String set_alarm;
	private String workplace;		// 판매사업장. 추후 영업부, 고객 따로 할경우 분리해야함.
	private String hp_no;
	private String email;

	// 거래처 정보
	private String cust_num;
	private String cust_nm;
	private String cust_seq;
	private String site_ref;
	
	// 사용자 구분
	private String gubun;
	
	// 푸쉬체크
	private boolean pushChk;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHp_no() {
		return hp_no;
	}
	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}
	public String getUser_id() {
		return user_id == null ? "" : user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPasswd() {
		return passwd == null ? "" : passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUser_nm() {
		return user_nm == null ? "" : user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getAuth() {
		return auth == null ? "" : auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getSet_item_group() {
		return set_item_group == null ? "" : set_item_group;
	}

	public void setSet_item_group(String set_item_group) {
		this.set_item_group = set_item_group;
	}

	public String getSet_alarm() {
		return set_alarm == null ? "" : set_alarm;
	}

	public void setSet_alarm(String set_alarm) {
		this.set_alarm = set_alarm;
	}

	public String getWorkplace() {
		return workplace == null ? "" : workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getCust_num() {
		return cust_num == null ? "" : cust_num;
	}

	public void setCust_num(String cust_num) {
		this.cust_num = cust_num;
	}

	public String getCust_nm() {
		return cust_nm == null ? "" : cust_nm;
	}

	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}

	public String getCust_seq() {
		return cust_seq == null ? "" : cust_seq;
	}

	public void setCust_seq(String cust_seq) {
		this.cust_seq = cust_seq;
	}

	public String getSite_ref() {
		return site_ref == null ? "" : site_ref;
	}

	public void setSite_ref(String site_ref) {
		this.site_ref = site_ref;
	}

	public String getGubun() {
		return gubun == null ? "" : gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public boolean isPushChk() {
		return pushChk;
	}

	public void setPushChk(boolean pushChk) {
		this.pushChk = pushChk;
	}
}
