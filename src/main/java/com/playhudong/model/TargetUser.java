package com.playhudong.model;

public class TargetUser {

	private final int id;
	private int groupId;
	private String userName;
	private String mobileNum;
	private String email;
	private String openId;
	private String weiboId;

	/**
	 * use to read target_user from db
	 * 
	 * @param id
	 *            user id
	 * @param groupId
	 *            group id of the user
	 * @param userName
	 *            user's name
	 * @param mobileNum
	 *            user's mobile number
	 * @param email
	 *            user's email
	 * @param openId
	 *            user's openId of Weixin
	 * @param weiboId
	 *            user's id of Weibo
	 */
	public TargetUser(Integer id, Integer groupId, String userName, String mobileNum, String email, String openId,
			String weiboId) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.userName = userName;
		this.mobileNum = mobileNum;
		this.email = email;
		this.openId = openId;
		this.weiboId = weiboId;
	}

	/**
	 * use user info to create a target-user
	 * @param groupId
	 * @param userName
	 * @param mobileNum
	 * @param email
	 * @param openId
	 * @param weiboId
	 */
	public TargetUser(int groupId, String userName, String mobileNum, String email, String openId, String weiboId) {
		super();
		this.id = -1;// when we insert a t_user into a table, db will
						// automatically calculate a id
		this.groupId = groupId;
		this.userName = userName;
		this.mobileNum = mobileNum;
		this.email = email;
		this.openId = openId;
		this.weiboId = weiboId;
	}

	
	
	
	@Override
	public String toString() {
		return "TargetUser [id=" + id + ", groupId=" + groupId + ", userName=" + userName + ", mobileNum=" + mobileNum
				+ ", email=" + email + ", openId=" + openId + ", WeiboId=" + weiboId + "]";
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public int getId() {
		return id;
	}
	
	
	
	

}
