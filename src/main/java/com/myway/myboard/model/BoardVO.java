package com.myway.myboard.model;

import java.util.Date;

public class BoardVO {
	private Integer seq;
	private String title;
	private String writer;
	private Integer hit;
	private Date regdate;
	private String content;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "BoardPost [seq=" + seq + ", title=" + title + ", writer=" + writer + ", hit=" + hit + ", regdate="
				+ regdate + ", content=" + content + "]";
	}
	
	
	
}
