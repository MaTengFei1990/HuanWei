package com.hollysmart.utils;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;

public class BaiDuLatLng {
	public String[] bToG(double lat, double lng) {
		// x = 2 * x1 - x2;
		LatLng LatLng1 = new LatLng(lat, lng);
		// 将GPS设备采集的原始GPS坐标转换成百度坐标
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.GPS);
		// sourceLatLng待转换坐标
		converter.coord(LatLng1);
		LatLng LatLng2 = converter.convert();

		double mlng = 2 * LatLng1.longitude - LatLng2.longitude;
		double mlat = 2 * LatLng1.latitude - LatLng2.latitude;
		String[] latlngs = { mlat + "", mlng + "" };
		return latlngs;
	}

	public double[] bToG_double(double lat, double lng) {
		// x = 2 * x1 - x2;
		LatLng LatLng1 = new LatLng(lat, lng);
		// 将GPS设备采集的原始GPS坐标转换成百度坐标
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.GPS);
		// sourceLatLng待转换坐标
		converter.coord(LatLng1);
		LatLng LatLng2 = converter.convert();

		double mlng = 2 * LatLng1.longitude - LatLng2.longitude;
		double mlat = 2 * LatLng1.latitude - LatLng2.latitude;
		double[] latlngs = { mlat, mlng};
		return latlngs;
	}



	public String[] bToG(LatLng latLng) {
		// x = 2 * x1 - x2;
		// 将GPS设备采集的原始GPS坐标转换成百度坐标
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.GPS);
		// sourceLatLng待转换坐标
		converter.coord(latLng);
		LatLng LatLng2 = converter.convert();
		
		double mlng = 2 * latLng.longitude - LatLng2.longitude;
		double mlat = 2 * latLng.latitude - LatLng2.latitude;
		String[] latlngs = { mlat + "", mlng + "" };
		return latlngs;
	}




	
	public LatLng gToB(double lat, double lng){
		LatLng LatLng1 = new LatLng(lat, lng);
		// 将GPS设备采集的原始GPS坐标转换成百度坐标
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.GPS);
		// sourceLatLng待转换坐标
		converter.coord(LatLng1);
		LatLng LatLng2 = converter.convert();
		return LatLng2;
	}
	public static LatLng gToB(LatLng latLng){
		// 将GPS设备采集的原始GPS坐标转换成百度坐标
		CoordinateConverter converter = new CoordinateConverter();
		converter.from(CoordType.GPS);
		// sourceLatLng待转换坐标
		converter.coord(latLng);
		LatLng LatLng2 = converter.convert();
		return LatLng2;
	}
}
