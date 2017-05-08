package com.qs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final Date getNowDate() {
			return new Date(System.currentTimeMillis());
      }
	/**
	 * 将日期格式的字符串转换为长整型
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static long convert2long(String date) {
		try {
			String format = DateUtil.TIME_FORMAT;
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(date).getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}
	
	public static Integer convertToInt(String date) {
		String dateStr="";
		try {
			String format = DateUtil.TIME_FORMAT;
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat sf = new SimpleDateFormat(format);
				long l= sf.parse(date).getTime();
				dateStr=String.valueOf(l);
				if(null!=dateStr&&dateStr.length()>=10){
					dateStr=dateStr.substring(0,10);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int dateNum=0;
		if(null!=dateStr){
			dateNum=Integer.parseInt(dateStr);
		}
		
		return dateNum;
	}
	
	/**
	 * 把当前的时间转int
	 * @return
	 */
	public static Integer currentTimeToInt(){
		String currentTime=String.valueOf(System.currentTimeMillis());
		String time=currentTime.substring(0,10);
		return Integer.parseInt(time);
	}
	
	

	
	 /** 
	  * 将长整型数字转换为日期格式的字符串 
	  *  
	  * @param time 
	  * @param format 
	  * @return 
	  */  
	 public static String convert2String(long time) {  
	   String format = DateUtil.TIME_FORMAT;
	   if(time>0l) {  
	   if (StringUtils.isBlank(format))  
	    format =  DateUtil.TIME_FORMAT;  
	   SimpleDateFormat sf = new SimpleDateFormat(format);  
	   Date date = new Date(time);  
	   return sf.format(date);  
	  }  
	  return "";  
	 } 
	 

	 /**
     * 日期设置一年 分周提示
     * @return Map<String,List<String>>
     * @throws ParseException
     */
    public static Map<String,List<String>> getAgentInfoDateTime() throws ParseException {
        //请注意月份是从0-11
        Calendar start = Calendar.getInstance();
        start.set(2016,9, 1);//月份从0开始，这里从2016年10月开始。
        Calendar end = Calendar.getInstance();
        //end.set(2017, 3, 1);
        end.setTime(new Date());

        int sunday = 0;
        int monDay = 0;
        int year = 0;
        String mon = null;
        String sun = null;
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<String> dataList = new ArrayList<String>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM月dd日");
        while(start.compareTo(end) <= 0) {
            int w = start.get(Calendar.DAY_OF_WEEK);
            if (w == Calendar.MONDAY) {//星期一
                mon = formatMonth.format(start.getTime());
                monDay = 1;
            }
            if (w == Calendar.SUNDAY) {//1为星期天，7为星期六
                if (monDay == 1) {
                    sun = formatMonth.format(start.getTime());
                    sunday = 1;
                }

            }
            if (monDay == sunday && monDay == 1) {
                if (year == 0) year = start.get(Calendar.YEAR);
                if (year == start.get(Calendar.YEAR)) {
                    String data = mon + "--" + sun;
                    dataList.add(data);
                } else {
                    if (year != 0) {
                        map.put("a" + year, dataList);
                        dataList = new ArrayList<String>();
                        year += 1;
                    }
                }
                monDay = 0;
                sunday = 0;
                mon = null;
                sun = null;
            }
            //打印每天2
            //System.out.println(format.format(start.getTime()));
            //循环，每次天数加1
            start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
        }
        map.put("a" + year, dataList);//当前年的数据。
        //System.out.println("map = " + map);
        return map;
    }
    
    /**
     * 获取参数时间为该年第几周
     * @param dateTime
     * @return
     * @throws ParseException
     */
    public static int getDateTOWeek (String dateTime) throws ParseException{
    	int toWeek=0;
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
    	Date date  = format.parse(dateTime);  
    	Calendar calendar = Calendar.getInstance();  
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);  
    	calendar.setTime(date);  
    	toWeek=calendar.get(Calendar.WEEK_OF_YEAR);  
    	return toWeek;
	}
    
    /**
     * 根据参数时间获取该周最后天时间
     * @param dateTime
     * @return z周日
     * @throws ParseException
     */
    public static String getDatalastDay(String dateTime){
    	Calendar cal = Calendar.getInstance();

		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		//所在周开始日期
		//new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		//所在周结束日期
		dateTime=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return dateTime;
    }
    /**
     * 获取参数时间周一日期
     * @param time
     * @return
     * @throws ParseException
     * @author:zyy
     * @time:2017年3月30日
     */
    public static String getDateFilstDay(String time) {

    	Calendar cal = Calendar.getInstance();

    	try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}

    	int d = 0;
    	if(cal.get(Calendar.DAY_OF_WEEK)==1){
    		d = -6;
    	}else{
    		d = 2-cal.get(Calendar.DAY_OF_WEEK);
    	}
    	cal.add(Calendar.DAY_OF_WEEK, d);
    	time=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    	return time;
    }
    /**
     * 获取当前时间
     * @return String 时间 yyyy-MM-dd
     */
    public static String getNewDate(){
    	String date="";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	date=sdf.format(new Date());
    	return date;
    }
    /**
     * 获取当前时间
     * @return String 时间yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDates(){
    	String date="";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	date=sdf.format(new Date());
    	return date;
    }
    
    /**
	 * 
	 * @param time当前时间
	 * @num推迟几天
	 * @return
	 * @throws ParseException
	 */
	public static String getEndDate(String time,int num) throws ParseException{
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = new GregorianCalendar();
		Date da=sdf.parse(time);
		lastDate.setTime(da);
		lastDate.roll(Calendar.DATE, -num);//日期回滚7天
	    time=sdf.format(lastDate.getTime());*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String returnTime="";
		Calendar cal=Calendar.getInstance();
		cal.setTime(sdf.parse(time));
		long longTime=cal.getTimeInMillis();
		longTime=longTime-(24*60*60*1000)*num;
		returnTime=convert2String(longTime);
		
		return returnTime.substring(0,returnTime.indexOf(" "));
	}
	
	/**
	 * 获取当前时间的上月时间往后推一个月时间
	 * @return String
	 */
	public static String getPreMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String preMonth = dft.format(cal.getTime());
		return preMonth;
	}
	/**
	 * 获取当前时间加1天
	 * @param date
	 * @return
	 * @author:zyy
	 * @time:2017年3月30日
	 */
	public static String getNextDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return sdf.format(date);
    }
	
	  public static String getDatalastWeek(){
	    	
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		    Calendar date=Calendar.getInstance(Locale.CHINA);

		    date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天

		    date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周

		    date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天
		    
			return sdf.format(date.getTime());
	    }
    public static void main(String[] args) throws Exception {

		//System.out.println(DateUtil.convert2long("2017-02-13 00:00"));
		Long l=1490152553468l;
		
		System.out.println(convertToInt("2017-03-30 12:02:01"));
		//System.out.println(getNextDay(new Date()));

		//所在周结束日期
		//System.out.println(getDateFilstDay("2017-03-30"));
		System.out.println(getDatalastWeek());
	}
    
	public static String getCurrentDayStr(){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String curDate=sdf.format(new Date());
		return curDate;
	}
    

}
