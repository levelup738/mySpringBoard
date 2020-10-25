package com.myway.myboard.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.constraints.Length;

public class UserVO {
	private Integer seq;
	@Pattern(regexp = "^[가-힣]{2,10}$")
	private String name;
	@Pattern(regexp = "^[a-zA-Z0-9]{4,15}$")
	private String id;
	@Pattern(regexp = "^.*(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()-+]).{8,13}$")
	private String pw;
	private String pwre;
	@NotEmpty
	private String gender;
	@Pattern(regexp = "^[0-9]{10,11}$")
	private String hp;
	@Pattern(regexp = "^[0-9]{4}+-[0-9]{2}+-[0-9]{2}$")
	private String birth;
	@NotEmpty
	@Email
	private String email;
	private Date regdate;
	private String authkey;
	private Integer authstatus;
	
	@NotEmpty
	private String birth_year;
	@NotEmpty
	private String birth_month;
	@NotEmpty
	private String birth_day;
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPwre() {
		return pwre;
	}
	public void setPwre(String pwre) {
		this.pwre = pwre;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getAuthkey() { return authkey; }
	public void setAuthkey(String authkey) { this.authkey = authkey; }
	public Integer getAuthstatus() { return authstatus; }
	public void setAuthstatus(Integer authstatus) { this.authstatus = authstatus; }

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "UserVO{" +
				"seq=" + seq +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				", pw='" + pw + '\'' +
				", pwre='" + pwre + '\'' +
				", gender='" + gender + '\'' +
				", hp='" + hp + '\'' +
				", birth='" + birth + '\'' +
				", email='" + email + '\'' +
				", regdate=" + regdate +
				", authkey='" + authkey + '\'' +
				", authstatus=" + authstatus +
				", birth_year='" + birth_year + '\'' +
				", birth_month='" + birth_month + '\'' +
				", birth_day='" + birth_day + '\'' +
				'}';
	}
}
