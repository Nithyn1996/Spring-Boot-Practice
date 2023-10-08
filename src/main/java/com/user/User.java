package com.user;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    @JsonProperty("id")
    private int id = 0;

    @JsonProperty("role_id")
    private int roleId = 0;

    @JsonProperty("user_name")
    private String userName = "";

    @JsonProperty("email_address")
    private String emailAddress = "";

    @JsonProperty("phone_number")
    private long phoneNumber = 0;

    @JsonProperty("salary")
    private float salary = 0.0f;

    @JsonProperty("skills")
    private List<String> skills = null;

	public User() {
	}

	 public User( int id, int roleId, String userName, String emailAddress, long phoneNumber, float salary, List<String> skills) {
	         this.id = id;
	         this.roleId = roleId;
	         this.userName = userName;
	         this.emailAddress = emailAddress;
	         this.phoneNumber = phoneNumber;
	         this.salary = salary;
	         this.skills = skills;
	    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "User ["+ "id=" + id + ", "
				+ "roleId=" + roleId + ", "
				+ "userName=" + userName + ", "
				+ "emailAddress=" + emailAddress + ", "
				+ "phoneNumber=" + phoneNumber + ", "
				+ "salary=" + salary + "]";
	}
}