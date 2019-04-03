package com.hollysmart.values;

import java.io.Serializable;
import java.util.List;

public class UnitDetailInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String scope;
	private String android;
	private String source_id;
	private String home_image;
	private String face_image;
	private String face_image_1;
	private String thumb_url;
	private int unit_id;
	private int saygood;
	private String file_name;
	private String type_code;
	private String author;
	private String created;
	private String address;
	private String comment_num;
	private int unit_type;
	private double longitude;
	private double latitude;
	private String rating;
	private String unit_name;
	private String brief;
	private List<UnitDetailInfo> recommend;
	private String tag;
	private boolean hasData;
	
	private String remark;
	private String state;
	private String coorperate;
	private String equipment;
	private String open_time_range;
	private String id;
	private String open_time;
	private String enterpise_no;
	private String rate_info;
	private String special_offer;
	private String init_time;
	private String traffic_info;
	private String price_average;
	private String contact_phone;
	private String secret_offer;
	private String contact_person;
	private String hint;
	private String contact_info;
	private String modified;
	private String discount;
	private String editor;
	private String promote;
	private String price_min;
	private String price_info;
	private String buzi_scope;
	private String lang_id;
	private String introduction;
	private String price_max;
	private List<ImageInfo> images;
//	private CommentInfo comment;
	private String androidapp;
//	private AppInfo app;
	private boolean allTag;
	
	
	public UnitDetailInfo() {
	}
	

	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAndroid() {
		return android;
	}
	public void setAndroid(String android) {
		this.android = android;
	}
	public String getSource_id() {
		return source_id;
	}
	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}
	public String getHome_image() {
		return home_image;
	}
	public void setHome_image(String home_image) {
		this.home_image = home_image;
	}
	public String getFace_image() {
		return face_image;
	}
	public void setFace_image(String face_image) {
		this.face_image = face_image;
	}
	public String getFace_image_1() {
		return face_image_1;
	}
	public void setFace_image_1(String face_image_1) {
		this.face_image_1 = face_image_1;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public int getSaygood() {
		return saygood;
	}
	public void setSaygood(int saygood) {
		this.saygood = saygood;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public int getUnit_type() {
		return unit_type;
	}
	public void setUnit_type(int unit_type) {
		this.unit_type = unit_type;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public List<UnitDetailInfo> getRecommend() {
		return recommend;
	}
	public void setRecommend(List<UnitDetailInfo> recommend) {
		this.recommend = recommend;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

	public boolean isAllTag() {
		return allTag;
	}
	public void setAllTag(boolean allTag) {
		this.allTag = allTag;
	}

//	public AppInfo getApp() {
//		return app;
//	}
//
//	public void setApp(AppInfo app) {
//		this.app = app;
//	}

	public String getAndroidapp() {
		return androidapp;
	}

	public void setAndroidapp(String androidapp) {
		this.androidapp = androidapp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCoorperate() {
		return coorperate;
	}

	public void setCoorperate(String coorperate) {
		this.coorperate = coorperate;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getOpen_time_range() {
		return open_time_range;
	}

	public void setOpen_time_range(String open_time_range) {
		this.open_time_range = open_time_range;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getEnterpise_no() {
		return enterpise_no;
	}

	public void setEnterpise_no(String enterpise_no) {
		this.enterpise_no = enterpise_no;
	}

	public String getRate_info() {
		return rate_info;
	}

	public void setRate_info(String rate_info) {
		this.rate_info = rate_info;
	}

	public String getSpecial_offer() {
		return special_offer;
	}

	public void setSpecial_offer(String special_offer) {
		this.special_offer = special_offer;
	}

	public String getInit_time() {
		return init_time;
	}

	public void setInit_time(String init_time) {
		this.init_time = init_time;
	}

	public String getTraffic_info() {
		return traffic_info;
	}

	public void setTraffic_info(String traffic_info) {
		this.traffic_info = traffic_info;
	}

	public String getPrice_average() {
		return price_average;
	}

	public void setPrice_average(String price_average) {
		this.price_average = price_average;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getSecret_offer() {
		return secret_offer;
	}

	public void setSecret_offer(String secret_offer) {
		this.secret_offer = secret_offer;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getPromote() {
		return promote;
	}

	public void setPromote(String promote) {
		this.promote = promote;
	}

	public String getPrice_min() {
		return price_min;
	}

	public void setPrice_min(String price_min) {
		this.price_min = price_min;
	}

	public String getPrice_info() {
		return price_info;
	}

	public void setPrice_info(String price_info) {
		this.price_info = price_info;
	}

	public String getBuzi_scope() {
		return buzi_scope;
	}

	public void setBuzi_scope(String buzi_scope) {
		this.buzi_scope = buzi_scope;
	}

	public String getLang_id() {
		return lang_id;
	}

	public void setLang_id(String lang_id) {
		this.lang_id = lang_id;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPrice_max() {
		return price_max;
	}

	public void setPrice_max(String price_max) {
		this.price_max = price_max;
	}

	public List<ImageInfo> getImages() {
		return images;
	}

	public void setImages(List<ImageInfo> images) {
		this.images = images;
	}

//	public CommentInfo getComment() {
//		return comment;
//	}
//
//	public void setComment(CommentInfo comment) {
//		this.comment = comment;
//	}

}
