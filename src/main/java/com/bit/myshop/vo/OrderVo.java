package com.bit.myshop.vo;

public class OrderVo {
	private Long no;
	private String pname;
	private Long price;
	private String reg_date;
	private Long userNo;
	private String userName;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getRegDate() {
		return reg_date;
	}
	public void setRegDate(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", pname=" + pname + ", price=" + price + ", reg_date=" + reg_date
				+ ", userNo=" + userNo + "]";
	}
}
