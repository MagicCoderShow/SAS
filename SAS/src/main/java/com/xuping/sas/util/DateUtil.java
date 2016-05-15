package com.xuping.sas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * 
 * @author 王启靖
 * 
 */
@SuppressWarnings("unused")
public class DateUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat();

	public static Integer getYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR);
	}

	public static Integer getMonth() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int dom = cal.get(Calendar.DAY_OF_MONTH);
		int doy = cal.get(Calendar.DAY_OF_YEAR);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static String formatDate(Date date, String pattern) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	/**
	 * 5天以后的日期
	 * 
	 * @param n
	 * @param starttime
	 * @return
	 */
	public static Date afterDay(int n, Date starttime) {
		Calendar c = Calendar.getInstance();
		c.setTime(starttime);
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		return d2;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	
	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		long hourdif = (time2 - time1) % (1000 * 3600 * 24) / 1000 * 60 * 60;

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的天数小时数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static String daysAndHorusBetween(Date smdate, Date bdate)
			throws ParseException {
		if (smdate.after(bdate)) {
			return "-1";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600);// 相差总共的小时数
		long hourdif1 = between_days / 24;// 得到的天数
		long hourdif2 = between_days % 24;

		if (hourdif1 == 0) {
			if (hourdif2 == 0) {
				return "-2";
			} else {
				return hourdif2 + "小时";
			}
		}

		return hourdif1 + "天" + hourdif2 + "小时";
	}


	/**
	 * 计算两个日期之间相差的秒数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int secondBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 1000;
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date getValidtime(Date date, int valid)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.parse(sdf.format(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long time1 = cal.getTimeInMillis();
		long time2 = time1 + valid * 60 * 1000;
		return new Date(time2);
	}
	public static Date gettime(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(time);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Date();
	}

	public static List<Date> getDatesByTimes(Date starttime,Date endtime){
		Calendar start = Calendar.getInstance();  
		start.setTime(starttime);
	    Long startTIme = start.getTimeInMillis();  
	  
	    Calendar end = Calendar.getInstance();  
	    end.setTime(endtime);
	    Long endTime = end.getTimeInMillis();  
	    //加一天
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	  
	    Long time = startTIme;  
	    List<Date> ds = new ArrayList<Date>();
	    while (time <= endTime) {  
	        Date d = new Date(time);  
	        ds.add(d);
	        time += oneDay;  
	    }  
	    return ds;
	}
	public static String time(Date time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(time);
			return date;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	public static Date longtime(long TimeMillis){
		Date d=new Date();
		d.setTime(TimeMillis);
		return d;
	}
	public static String getDatetime() {
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(d);
		return date;
	}
	public static Date getDate() throws ParseException {
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return sdf.parse(date);
	}
	public static String getDateStr() throws ParseException {
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return date;
	}
	public static void main(String[] args) {
		
/*        java.util.Calendar rightNow = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //得到当前时间，+3天
        rightNow.add(java.util.Calendar.DAY_OF_MONTH, (int) 0.3);   
        //如果是后退几天，就写 -天数 例如：
       // rightNow.add(java.util.Calendar.DAY_OF_MONTH, -3);
        //进行时间转换
        String date = sim.format(rightNow.getTime()); 
        System.out.println(date);*/
		/*System.out.println(time(new Date()));
		Date d = new Date();
		d.setMonth(d.getMonth()+4);
		List<Date> ds = getDatesByTimes(new Date(),d );
		System.out.println(ds);
		*/
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
       
        
	}
	public static String getFirstDate(){
		//获取当前月第一天：
        Calendar c = Calendar.getInstance();   
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = DateUtil.formatDate(c.getTime(),"yyyy-MM-dd");
        return first;
	}
	public static String getLastDate(){
		//获取当前月最后一天
        Calendar ca = Calendar.getInstance();   
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String last = DateUtil.formatDate(ca.getTime(),"yyyy-MM-dd");
        return last;
	}
	
	/**
	 * String 转date
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str,String pattern) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(pattern); 
		Date date=sdf.parse(str);  
		return date;
	}
}
