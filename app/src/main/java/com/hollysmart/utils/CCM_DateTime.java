package com.hollysmart.utils;

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * create:2011,wl
 * update:2011-8-17,wl,getMonthDays();
 */
public class CCM_DateTime {
	/**
	 * 获取系统日期
	 * @return 2011-01-01
	 */
	public String Date(){
		
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		int month =t.month;
		int day =t.monthDay;
		return mergeDateString(year,month,day);
	}
	/**
	 * 获取系统日期
	 * @return 20110101
	 */
	public String Date_No(){
		
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		int month =t.month;
		int day =t.monthDay;
		return mergeDateString_No(year,month,day);
	}
	/**
	 * 获取系统年月
	 * @return 2011-01
	 */
	public String Month(){
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		int month =t.month;
		return mergeMonthString(year,month);
	}
	/**
	 * 获取系统下个月
	 * @return 2011-02
	 */
	public String NextMonth(){
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		int month =t.month+1;
		if(month==12){
			year+=1;
			month = 0;
		}
		return mergeMonthString(year,month);
	}
	/**
	 * 获取系统时间
	 * @return 14：20
	 */
	public String Time(){
		Time t=new Time();
	    t.setToNow();
		int hour = t.hour;
		int minute  = t.minute;
		
		return mergeTimeString(hour,minute);
	}
	/**
	 * 获取系统日期时间
	 * @return 2011-01-01 14：20
	 */
	public String Datetime(){
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		int month =t.month;
		int day =t.monthDay;
		int hour = t.hour;
		int minute  = t.minute;
		return mergeDatetimeString(year,month,day,hour,minute);
	}
	/**
	 * 获取系统日期时间
	 * @return 2011-01-01 14:20:20
	 */
	public String Datetime2(){
		Time t=new Time();
		t.setToNow();
		int year =t.year;
		int month =t.month;
		int day =t.monthDay;
		int hour = t.hour;
		int minute  = t.minute;
		int second = t.second;
		return mergeDatetimeString2(year,month,day,hour,minute, second);
	}
	/**
	 * 获取系统日期
	 * @return 201101011420
	 */
	public String DateTime_No(){
		Time t=new Time();
	    t.setToNow();
	    int year =t.year;
		int month =t.month;
		int day =t.monthDay;
		int hour = t.hour;
		int minute  = t.minute;
		return mergeDatetimeString_No(year,month,day,hour,minute);
	}
	/**
	 * 获取系统——年
	 * @return 2011
	 */
	public int getYear(){
		//获取系统何年
		Time t=new Time();
	    t.setToNow();
		int year =t.year;
		return year;
	}
	/**
	 * 获取系统——月
	 * @return 11
	 */
	public int getMonth(){
		Time t=new Time();
	    t.setToNow();
		int month =t.month+1;
		return month;
	}
	/**
	 * 获取系统——日
	 * @return 31
	 */
	public int getDay(){
		Time t=new Time();
	    t.setToNow();
		int day =t.monthDay;
		return day;
	}
	/**
	 * 获取系统——时
	 * @return 24
	 */
	public int getHour(){
		Time t=new Time();
		t.setToNow();
		int hour =t.hour;
		return hour;
	}
	/**
	 * 获取系统——分
	 * @return 59
	 */
	public int getMinute(){
		Time t=new Time();
		t.setToNow();
		int minute =t.minute;
		return minute;
	}
	/**
	 * 转换日期格式
	 * @param time
	 * @return
	 */
	public String TransformDate(long time){
		SimpleDateFormat simpleDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return simpleDateTime.format(new Date(time));
	}
	/**
	 * 计算0000-00~0000-00之间月份数
	 * @return 31
	 */
	public int NumberOfMonth(int year,int month){
		return 12*(year-getYear())+month-getMonth();
	}
	public String getNextYear(String m){
		int year = Integer.valueOf(m) + 1;
		return ""+year;
	}
	public String getLastYear(String m){
		int year = Integer.valueOf(m) - 1;
		return ""+year;
	}
	public String getNextMonth(String m){
		int year = Integer.valueOf(m.substring(0, 4));
		int month = Integer.valueOf(m.substring(5))-1;
		month += 1;
		if(month==12){
			year += 1;
			month = 0;
		}
		return mergeMonthString(year,month);
	}
	public String getLastMonth(String m){
		int year = Integer.valueOf(m.substring(0, 4));
		int month = Integer.valueOf(m.substring(5))-1;
		month -= 1;
		if(month==-1){
			year -= 1;
			month = 11;
		}
		return mergeMonthString(year,month);
	}
	//格式化日期
	public String mergeDateString(int y, int m, int d) {
		String M = format(m+1);
		String D = format(d);
		String dt = new StringBuilder().append(y).append("-").append(M)
				.append("-").append(D).toString();
		return dt;

	}
	//格式化日期
	public String mergeDateString_No(int y, int m, int d) {
		String M = format(m+1);
		String D = format(d);
		return y+M+D;

	}
	public String mergeMonthString(int y, int m) {
		String M = format(m+1);
		String dt = new StringBuilder().append(y).append("-")
				.append(M).toString();
		return dt;

	}
	public String mergeTimeString(int h, int min) {
		String H = format(h);
		String Min = format(min);
		String dt = new StringBuilder().append(H).append(":")
				.append(Min).toString();
		return dt;

	}
	public String mergeDatetimeString(int y, int m, int d, int h, int min) {
		String M = format(m+1);
		String D = format(d);
		String H = format(h);
		String Min = format(min);
		String dt = new StringBuilder().append(y).append("-").append(M)
				.append("-").append(D).append(" ").append(H).append(":").append(Min).toString();
		return dt;

	}
	public String mergeDatetimeString2(int y, int m, int d, int h, int min, int second) {
		String M = format(m+1);
		String D = format(d);
		String H = format(h);
		String Min = format(min);
		String Sec = format(second);
		String dt = new StringBuilder().append(y).append("-").append(M)
				.append("-").append(D).append(" ").append(H).append(":").append(Min).append(":").append(Sec).toString();
		return dt;
	}
	public String mergeDatetimeString_No(int y, int m, int d, int h, int min) {
		String M = format(m+1);
		String D = format(d);
		String H = format(h);
		String Min = format(min);
		return y+M+D+H+Min;

	}
    private final static long DAY_MILLIS = 86400000;
	/**
     * 计算两个日期相差的天
     * @param start
     * @param end
     */
    public long DayNumber(long start, long end) {
		return (end-start) / DAY_MILLIS;
    }
	
	/** 
	   * 得到几天前的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public long getDateBefore(Long d, int day){
	   Calendar now = Calendar.getInstance();
	   now.setTimeInMillis(d);
//	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
	   return  now.getTimeInMillis();
	  }  
	  /** 
	   * 得到几天前的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public String getDateBefore(int day){
	   Calendar now = Calendar.getInstance();
	   now.setTimeInMillis(System.currentTimeMillis());
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
	   return  mergeDateString(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
	  }  
	  /** 
	   * 得到几天后的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public long getDateAfter(Long d, int day){
	   Calendar now = Calendar.getInstance();
	   now.setTimeInMillis(d);  
//	   now.setTime(d);  
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
	   now.set(Calendar.HOUR_OF_DAY, 0);
	   now.set(Calendar.MINUTE,0);
	   now.set(Calendar.SECOND,0);
	   now.set(Calendar.MILLISECOND,0);
	   return now.getTimeInMillis();
	  }  
	  /** 
	   * 得到几天后的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public String getDateAfter(int day){
	   Calendar now = Calendar.getInstance();
	   now.setTimeInMillis(System.currentTimeMillis());
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
	   return mergeDateString(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
	  }  
	  /** 
	   * 得到几月前的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	  public String getMonthBefore(int month){
		   Calendar now = Calendar.getInstance();
		   now.setTimeInMillis(System.currentTimeMillis());
		   now.set(Calendar.MONTH,now.get(Calendar.MONTH)-month);
		   return mergeMonthString(now.get(Calendar.YEAR), now.get(Calendar.MONTH));
		  }  
	//格式化字符
	private String format(int x){
		String s = "" + x;
		if(s.length() == 1)
				s = "0" + s;
		return s;
	}
	public String getRepaymentDate(int year, int month, int zdDate, int MXQ, boolean frist){
		int days_left;
		if(frist){
			days_left = zdDate+MXQ;
		}else{
			days_left = MXQ;
			if(month==11){
				year += 1;
				month = 0;
			}else{
				month += 1;
			}
		}
		int days_next = getMonthDays(year,month);
		if(days_left <= days_next){
			return mergeDateString(year, month, days_left );
		}else{
			return getRepaymentDate(year,month,zdDate,MXQ-(days_next-zdDate),false);
		}
		
	}
	/**
	 * 获得指定月份共有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public int getMonthDays(int year, int month) {
		switch (month) {
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11: {
			return 31;
		}
		case 3:
		case 5:
		case 8:
		case 10: {
			return 30;
		}
		case 1: {
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
				return 29;
			else
				return 28;
		}
		}
		return 0;
	}
	/**
	 * 获得指定月份的1号是周几
	 * @param year
	 * @param month
	 * @return
	 */
	public int getWeekNumber(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	public Date timeMillisToDate(long mi){
		//mi += CCM_Values.Time_Zone;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(mi);
		return c.getTime();
	}
	
	public String dateToString(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	public long timeInMillis(){
		Calendar c = Calendar.getInstance();
//		c.setTime(new Date());
		return c.getTimeInMillis();

	}
	
	/**
	 * 格式化日期 yyyy-MM-dd HH:mm:ss 2011-12-29 12:42:27 to 今天 12:42
	 * 
	 * @param time
	 */
	public String FormatSimpleDate(String t) {
		if (t != null && !t.equals("")) {
			int y = Integer.parseInt(t.substring(0, 4));
			int dm = Integer.parseInt(t.substring(5, 7));
			int d = Integer.parseInt(t.substring(8, 10));
			String h = t.substring(11, 13);
			String m = t.substring(14, 16);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nt = sdf.format(date);
			int ny = Integer.parseInt(nt.substring(0, 4));
			int ndm = Integer.parseInt(nt.substring(5, 7));
			int nd = Integer.parseInt(nt.substring(8, 10));
			if (y == ny && dm == ndm && d == nd) {
				return "今天" + " " + h + ":" + m;
			} else if (y == ny && dm == ndm && nd - d == 1) {
				return "昨天" + " " + h + ":" + m;
			} else if (y == ny) {
				return dm + "-" + d + " " + h + ":" + m;
			} else
				return y + "-" + dm + "-" + d + " " + h + ":" + m;
		}
		return "";
	}
	
	public String DateFormat(String Date){
    	String date;
    	String Year = Date.substring(0, 4);
    	String Month = Date.substring(4, 6);
    	String Day = Date.substring(6,8);
    	date = Year+"-"+Month+"-"+Day;
		return date;
    }
	public String DateFormat2(String Date, long Timestamp){
    	String date;
    	int Month = 0;
    	int Day = 0;
    	Pattern p = Pattern.compile("\\d*月");
		Matcher m = p.matcher(Date);
		if(m.find()){
			Month = Integer.valueOf(m.group().toString().substring(0, m.group().length()-1));
		}
		Pattern pp = Pattern.compile("\\d*日");
		Matcher mm = pp.matcher(Date);
		if(mm.find()){
			Day = Integer.valueOf(mm.group().toString().substring(0, mm.group().length()-1));
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(Timestamp);
		int smsYear = c.get(Calendar.YEAR);
		int smsMonth = c.get(Calendar.MONTH)+1;
		if(Math.abs(Month-smsMonth)<2){
			date = mergeDateString(smsYear, Month-1, Day);
		}else{
			date = mergeDateString(smsYear+1, Month-1, Day);
		}
		return date;
    }
	public String DateFormat3(String Date){
		String date;
		int Year = 0;
    	int Month = 0;
    	int Day = 0;
    	Pattern p = Pattern.compile("\\d*年");
    	Matcher m = p.matcher(Date);
		if(m.find()){
			String strYear = m.group().toString().substring(0, m.group().length()-1);
			if(strYear.length()==4){
				Year = Integer.valueOf(strYear);
			}else if(strYear.length()>4){
				Year = Integer.valueOf(strYear.substring(strYear.length()-4));
			}else if(strYear.length()==2){
				Year = Integer.valueOf("20"+strYear);
			}
		}
    	Pattern pp = Pattern.compile("\\d*月");
		Matcher mm = pp.matcher(Date);
		if(mm.find()){
				Month = Integer.valueOf(mm.group().toString().substring(0, mm.group().length()-1));
		}
		Pattern ppp = Pattern.compile("\\d*日");
		Matcher mmm = ppp.matcher(Date);
		if(mmm.find()){
				Day = Integer.valueOf(mmm.group().toString().substring(0, mmm.group().length()-1));
		}
		date = mergeDateString(Year, Month-1, Day);
		return date;
    }
	public String DateFormat4(String Date){
    	String date;
    	String Year = Date.substring(0, 4);
    	String Month = Date.substring(5, 7);
    	String Day = Date.substring(8);
		date = Year+"-"+Month+"-"+Day;
		return date;
    }
	
	/**
	 * 日期类型转换 ，String转成long
	 * 
	 * @param str
	 *            2012-01-01 00:00:00
	 * @return
	 */
	public long StringToLong(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d2 = null;
		try {
			d2 = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 将String to Date类型
		long t3 = d2.getTime();
		
		return t3;
	}
	
	/**
	 * 信息中心
	 * 格式化日期 yyyy-MM-dd HH:mm:ss 2011-12-29 12:42:27 
	 * 如果是当天只显示时间，否则只显示月日
	 * @param datetime 2012-05-07 15:51
	 * 
	 * */
	public String FormatSimpleDate_InfoCenter(String t){
		if (t != null && !t.equals("")) {
			int y = Integer.parseInt(t.substring(0, 4));
			int dm = Integer.parseInt(t.substring(5, 7));
			int d = Integer.parseInt(t.substring(8, 10));
			String h = t.substring(11, 13);
			String m = t.substring(14, 16);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nt = sdf.format(date);
			int ny = Integer.parseInt(nt.substring(0, 4));
			int ndm = Integer.parseInt(nt.substring(5, 7));
			int nd = Integer.parseInt(nt.substring(8, 10));
			if (y == ny && dm == ndm && d == nd) {
				return h + ":" + m;
			} else {
				return dm + "-" + d;
			}
		}
		return "";
	}
	
	
	
	/**
	 * 毫秒变成字符串       2分20秒
	 * @param milliseconds
	 * @return   
	 */
	public String millisecondsToString(int milliseconds){
		// 1秒 = 1000毫秒
		// 1分 = 60*1000
		// 1小时 = 60*60*1000
		
		int rant_hour = 60*60*1000;
		int rant_minute = 60*1000;
		int rant_seconds = 1000;
		
		String result = "";
		int yushu = milliseconds;
		int hour = yushu/rant_hour;
		yushu = yushu%rant_hour;
		
		if (hour != 0) {
			result = result+hour+"时";
		}
		
		int minute = yushu/rant_minute;
		yushu = yushu%rant_minute;
		
		if (minute != 0) {
			result = result+minute+"分";
		}
		
		int seconds = yushu/rant_seconds;
		if (seconds != 0) {
			result = result+seconds+"秒";
		}
		return result;
	}
	
}
