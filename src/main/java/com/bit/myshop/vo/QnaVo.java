package com.bit.myshop.vo;

public class QnaVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private Long userNo;
	private String userName;
	private Long chk;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	public Long getChk() {
		return chk;
	}
	public void setChk(Long chk) {
		this.chk = chk;
	}
	
	@Override
	public String toString() {
		return "QnaVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", userNo="
				+ userNo + ", userName=" + userName + ", chk=" + chk + "]";
	}
}
