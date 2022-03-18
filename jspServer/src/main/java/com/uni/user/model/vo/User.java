package com.uni.user.model.vo;

public class User {
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String citiNo;
	private String phone;
	private String nickName;
	private String email;
	private char gender;
	private char adminStatus;
	private char status;
	private int banCount;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String userPwd, String userName, String citiNo, String phone, char gender) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
		this.gender = gender;
	}

	public User(String userId, String userPwd, String userName, String citiNo, String phone, String nickName,
			String email, char gender) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
		this.nickName = nickName;
		this.email = email;
		this.gender = gender;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCitiNo() {
		return citiNo;
	}

	public void setCitiNo(String citiNo) {
		this.citiNo = citiNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(char adminStatus) {
		this.adminStatus = adminStatus;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getBanCount() {
		return banCount;
	}

	public void setBanCount(int banCount) {
		this.banCount = banCount;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", citiNo=" + citiNo + ", phone=" + phone + ", nickName=" + nickName + ", email=" + email
				+ ", gender=" + gender + ", adminStatus=" + adminStatus + ", status=" + status + ", banCount="
				+ banCount + "]";
	}
	
	

}
