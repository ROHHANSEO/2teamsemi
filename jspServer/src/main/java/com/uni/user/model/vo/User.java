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
	private String gender;
	private String adminStatus;
	private String status;
	private int banCount;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	
	public User(String userName, String citiNo, String phone) {
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
	}
	
	public User(String userId, String userName, String citiNo, String phone) {
		this.userId = userId;
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
	}

	public User(String userId, String userPwd, String userName, String citiNo, String phone, String gender) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
		this.gender = gender;
	}

	public User(String userId, String userPwd, String userName, String citiNo, String phone, String nickName,
			String email, String gender) {
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
	
	

	public User(int userNo, String userId, String userPwd, String userName, String citiNo, String phone,
			String nickName, String email, String gender, String adminStatus, String status, int banCount) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.citiNo = citiNo;
		this.phone = phone;
		this.nickName = nickName;
		this.email = email;
		this.gender = gender;
		this.adminStatus = adminStatus;
		this.status = status;
		this.banCount = banCount;
	}



	public User(int userNo, String userId, String userPwd, String userName, String citiNo, String phone,
			String nickName, String email, String gender) {
		super();
		this.userNo = userNo;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
