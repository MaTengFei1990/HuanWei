package com.hollysmart.utils;

public class PicDictToll {

	public static final int PIC_640 = 640;  
	public static final int PIC_590 = 590;  
	public static final int PIC_405 = 405;
	public static final int PIC_456 = 456;
	public static final int PIC_300 = 300;
	public static final int PIC_380 = 380;
	public static final int PIC_210 = 210;


	//新
	public static final int PIC_100 = 100;   //100*100
	public static final int PIC_500 = 500;	 //500*500
	public static final int PIC_180 = 180;	 //180*180




	private static String[] picType = { "-home.a.590","-face.a.405","-thumb.a.456","-face.a.300","-detail.a.380","-detail.a.210","-orig.a.640", "-image.w.100", "-image.w.500", "-image.w.180"};
	/**
	 * 获取合适尺寸的图片链接
	 * @param url   原图片链接
	 * @param type	本类中的  PIC_590...  0 返回原图
	 * @return 
	 */
	public static String getUrl(String url, int type){
		String caiUrl = url;
		switch (type) {
		case PIC_590:
			caiUrl += picType[0];
			break;
		case PIC_405:
			caiUrl += picType[1];
			break;
		case PIC_456:
			caiUrl += picType[2];
			break;
		case PIC_300:
			caiUrl += picType[3];
			break;
		case PIC_380:
			caiUrl += picType[4];
			break;
		case PIC_210:
			caiUrl += picType[5];
			break;
		case PIC_640:
			caiUrl += picType[6];
			break;
		case PIC_100:
			caiUrl += picType[7];
			break;
		case PIC_500:
			caiUrl += picType[8];
			break;
		case PIC_180:
			caiUrl += picType[9];
			break;
		}
		return caiUrl;
	}
}





















