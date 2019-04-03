package com.hollysmart.views.tag;

import java.io.Serializable;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private int textColorResId;
	private int bgResId;

	private boolean b;
	
	public Tag() {
	}
	
	public Tag(int paramInt, String paramString) {
		this.id = paramInt;
		this.title = paramString;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTextColorResId() {
		return textColorResId;
	}
	public void setTextColorResId(int textColorResId) {
		this.textColorResId = textColorResId;
	}
	public int getBgResId() {
		return bgResId;
	}
	public void setBgResId(int bgResId) {
		this.bgResId = bgResId;
	}

	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}
}
