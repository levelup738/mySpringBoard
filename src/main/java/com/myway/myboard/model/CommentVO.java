package com.myway.myboard.model;

import java.util.Date;

public class CommentVO {
	private Integer seq;
	private String writer;
	private String content;
	private Date regdate;
	private Integer boardseq;
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getBoardseq() {
		return boardseq;
	}
	public void setBoardseq(Integer boardseq) {
		this.boardseq = boardseq;
	}
	@Override
	public String toString() {
		return "CommentVO [seq=" + seq + ", writer=" + writer + ", content=" + content + ", regdate=" + regdate
				+ ", boardseq=" + boardseq + "]";
	}

}
