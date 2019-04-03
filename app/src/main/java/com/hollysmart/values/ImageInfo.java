package com.hollysmart.values;

import java.io.Serializable;

public class ImageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String thumb_url;
	private String ordering;
	private String relative_id;
	private String file_name;
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getOrdering() {
		return ordering;
	}
	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}
	public String getRelative_id() {
		return relative_id;
	}
	public void setRelative_id(String relative_id) {
		this.relative_id = relative_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



}
