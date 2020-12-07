package com.suimz.mybookshop.entity;

import java.io.Serializable;
/**
 * 用户实体类
 * @author suimz
 *
 */
public class Users implements Serializable {
	private static final long serialVersionUID = -8636725672050581611L;
	private Integer id;
	private String LoginId;
	private String LoginPwd;
	private String name;
	private String address;
	private String phone;
	private String mail;
	private Integer userRoleId;
	private Integer userStateId;
	
	public Users() {
	}

	
	
	
	public Users(String loginId, String loginPwd, String name, String address, String phone, String mail,
			Integer userRoleId, Integer userStateId) {
		super();
		LoginId = loginId;
		LoginPwd = loginPwd;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.mail = mail;
		this.userRoleId = userRoleId;
		this.userStateId = userStateId;
	}




	public String getLoginId() {
		return LoginId;
	}
	
	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getLoginPwd() {
		return LoginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		LoginPwd = loginPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getUserStateId() {
		return userStateId;
	}

	public void setUserStateId(Integer userStateId) {
		this.userStateId = userStateId;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", LoginId=" + LoginId + ", LoginPwd=" + LoginPwd + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", mail=" + mail + ", userRoleId=" + userRoleId + ", userStateId="
				+ userStateId + "]";
	}
	
}
