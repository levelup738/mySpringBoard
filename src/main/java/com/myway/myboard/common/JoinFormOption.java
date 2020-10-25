package com.myway.myboard.common;

import java.util.ArrayList;
import java.util.List;

public class JoinFormOption {
	private List<String> opt_genders;
	private List<String> opt_years;
	private List<String> opt_months;
	private List<String> opt_days;
	private List<String> opt_emails;
	
	public JoinFormOption() {
		super();
		opt_genders = new ArrayList<String>();
		opt_years = new ArrayList<String>();
		opt_months = new ArrayList<String>();
		opt_days = new ArrayList<String>();
		opt_emails = new ArrayList<String>();
		
		opt_genders.add("남");
		opt_genders.add("여");
		for(int year = 1980; year <= 2005; year++) {
			opt_years.add(year+"");
		}
		for(int month = 1; month <= 12; month++) {
			opt_months.add(month+"");
		}
		for(int day = 1; day <= 31; day++) {
			opt_days.add(day+"");
		}
		opt_emails.add("naver.com");
		opt_emails.add("gmail.com");
		opt_emails.add("daum.com");
		opt_emails.add("nate.com");
	}
	public List<String> getOpt_genders() {
		return opt_genders;
	}
	public void setOpt_genders(List<String> opt_genders) {
		this.opt_genders = opt_genders;
	}
	public List<String> getOpt_years() {
		return opt_years;
	}
	public void setOpt_years(List<String> opt_years) {
		this.opt_years = opt_years;
	}
	public List<String> getOpt_months() {
		return opt_months;
	}
	public void setOpt_months(List<String> opt_months) {
		this.opt_months = opt_months;
	}
	public List<String> getOpt_days() {
		return opt_days;
	}
	public void setOpt_days(List<String> opt_days) {
		this.opt_days = opt_days;
	}
	public List<String> getOpt_emails() {
		return opt_emails;
	}
	public void setOpt_emails(List<String> opt_emails) {
		this.opt_emails = opt_emails;
	}
	@Override
	public String toString() {
		return "JoinFormOption [opt_genders=" + opt_genders + ", opt_years=" + opt_years + ", opt_months=" + opt_months
				+ ", opt_days=" + opt_days + ", opt_emails=" + opt_emails + "]";
	}
	
	
}
