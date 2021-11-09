package com.infosys.infytel.order.dto;

import javax.validation.constraints.Email;

public class BuyerDTO {

	Integer buyerId;
	Long phoneNo;
	String name;
	@Email
	String email;
	String password;
	Integer rewardsPoint;
	boolean isPreviledged;
	boolean isActive;
	

	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRewardsPoint() {
		return rewardsPoint;
	}
	public void setRewardsPoint(Integer rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}
	public boolean getIsPreviledged() {
		return isPreviledged;
	}

	public void setIsPreviledged(boolean boolean1) {
		this.isPreviledged = boolean1;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public BuyerDTO() {
		super();
	}
	@Override
	public String toString() {
		return "BuyerDTO [buyerId=" + buyerId + ", phoneNo=" + phoneNo + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", rewardsPoint=" + rewardsPoint + ", isPreviledged=" + isPreviledged
				+ ", isActive=" + isActive + "]";
	}
	
}
