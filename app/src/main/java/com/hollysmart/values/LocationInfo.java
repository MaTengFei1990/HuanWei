package com.hollysmart.values;

import com.baidu.location.BDLocation;
import com.hollysmart.utils.BaiDuLatLng;

public class LocationInfo {
	private static LocationInfo locationInfo;

	private double lat;
	private double lng;

	private double glat;
	private double glng;

	private String addr;
	private boolean isScoll;

	private BaiDuLatLng baiDuLatLng;
	private LocationInfo() {
		baiDuLatLng = new BaiDuLatLng();
	}
	synchronized public static LocationInfo getInstance() {
		if (null == locationInfo)
			locationInfo = new LocationInfo();
		return locationInfo;
	}



	public void setLocation(BDLocation location){
		setLat(location.getLatitude());
		setLng(location.getLongitude());
		double[] latlng = baiDuLatLng.bToG_double(lat, lng);
		setGlat(latlng[0] - 0.00005);
		setGlng(latlng[1]);

		setAddr(location.getAddrStr());
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}


	public boolean isScoll() {
		return isScoll;
	}

	public void setScoll(boolean scoll) {
		isScoll = scoll;
	}


	public double getGlat() {
		return glat;
	}

	public void setGlat(double glat) {
		this.glat = glat;
	}

	public double getGlng() {
		return glng;
	}

	public void setGlng(double glng) {
		this.glng = glng;
	}
}











