package com.sirius.skymall.user.util.base;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Cleandar {
	public static int TimeDay(Map<String,Object> m)throws Exception{
			  SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyy/MM/dd");
			   Calendar d1 = Calendar.getInstance();
			  //开始时间
			  d1.setTime(dsf1.parse(m.get("START_DATE").toString()));
			  //结束时间
			  Calendar d2 = Calendar.getInstance();
			  d2.setTime(dsf1.parse(m.get("END_DATE").toString()));
			  //相差天数C
			  int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
			  d1 = (Calendar) d1.clone();
			  //不是同一年的情况下
			  while(d1.get(Calendar.YEAR) < d2.get(Calendar.YEAR)){
			   days = 365*(d2.get(Calendar.YEAR)-d1.get(Calendar.YEAR))+days-365;
			   
			   d1.add(Calendar.YEAR, d2.get(Calendar.YEAR)-d1.get(Calendar.YEAR));
			  }
			return days;
		}
	public static int TimeDay2(Map<String,Object> m)throws Exception{
		  SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyy-MM-dd");
		  
		  Calendar d1 = Calendar.getInstance();
		  //开始时间
		  d1.setTime(dsf1.parse(m.get("START_DATE").toString()));
		  //结束时间
		  Calendar d2 = Calendar.getInstance();
		  d2.setTime(dsf1.parse(m.get("END_DATE").toString()));
		  //相差天数C
		  int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		  d1 = (Calendar) d1.clone();
		  //不是同一年的情况下
		  while(d1.get(Calendar.YEAR) < d2.get(Calendar.YEAR)){
		   days = 365*(d2.get(Calendar.YEAR)-d1.get(Calendar.YEAR))+days-365;
		   
		   d1.add(Calendar.YEAR, d2.get(Calendar.YEAR)-d1.get(Calendar.YEAR));
		  }
		return days;
		}
	
	   public static Date covertDate(String date){
		     String str="yyyy-MM-dd HH:mm:ss";
		      SimpleDateFormat  dsf1 = new SimpleDateFormat(str);
			  Calendar d1 = Calendar.getInstance();
			  try{
			   Date date2= dsf1.parse(date);
			   return date2;
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return null;
	   }
	
	
	    // date 时间你要计算的时间
    	//day 为天数-day为在date的基础上减去day天day为在date的基础上加上day天
	   public static String getTime(int day,String date) {
		  try{
			  
			  String str="yyyy/MM/dd";
			  if(date.contains("-")){
				  str="yyyy-MM-dd";
				  
			  }
			  
		  SimpleDateFormat  dsf1 = new SimpleDateFormat(str);
		  Calendar d1 = Calendar.getInstance();
		  //开始时间
		   Date date2= dsf1.parse(date);
		   d1.setTime(date2);
		   d1.add(Calendar.DAY_OF_YEAR, day);
		    
		  
		   return dsf1.format(d1.getTime());
		  // System.out.println( dsf1.format( d1.getTime()));
		  }catch (Exception e) {
			e.printStackTrace();
		}
		  return "";
		  
	  }
	   public static String getTime(Date date) {
			  try{
			  SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyy/MM/dd");
			  
			   return dsf1.format(date.getTime());
			  // System.out.println( dsf1.format( d1.getTime()));
			  }catch (Exception e) {
				e.printStackTrace();
			}
			  return "";
	   }
			  public static String getTimeforH(Date date) {
				  try{
				  SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyy-MM-dd");
				  
				   return dsf1.format(date.getTime());
				  // System.out.println( dsf1.format( d1.getTime()));
				  }catch (Exception e) {
					e.printStackTrace();
				}
			  return "";
			  
		  }
	   
	   
	   public static String getTime() {
			  try{
			  SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			
			  
			   return dsf1.format(new Date());
			  // System.out.println( dsf1.format( d1.getTime()));
			  }catch (Exception e) {
				e.printStackTrace();
			}
			  return "";
			  
		  }
	   
	   
	   public static String getTimeForYHD(Date date) {
			  try{
			    SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyyMMdd");
			    return dsf1.format(date);
			  // System.out.println( dsf1.format( d1.getTime()));
			  }catch (Exception e) {
				e.printStackTrace();
			}
			  return "";
			  
		  }
	   
	   public static Date getCovertTimeForYHD(String date) {
			  try{
			    SimpleDateFormat  dsf1 = new SimpleDateFormat("yyyyMMdd");
			    return dsf1.parse(date);
			  // System.out.println( dsf1.format( d1.getTime()));
			  }catch (Exception e) {
				e.printStackTrace();
			}
			  return null;
			  
		  }
	   
	   
	   public static Date parseTime(String date){
		   
		   SimpleDateFormat dateFormat=null;
		   if(date.contains("/")){
			   dateFormat=new SimpleDateFormat("yyyy/MM/dd");
		   }else{
			   
			   dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			   
		   }
		   Date parseDate=null;
		   try {
			   parseDate=dateFormat.parse(date);
			  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
		 return parseDate;  
		   
		   
	   }
	   
       public static Date parseTimeforMoneth(String date){
		   SimpleDateFormat dateFormat=null;
		   if(date.contains("/")){
			   dateFormat=new SimpleDateFormat("yyyy/MM");
		   }else{
			   
			   dateFormat=new SimpleDateFormat("yyyy-MM");
			   
		   }
		   Date parseDate=null;
		   try {
			   parseDate=dateFormat.parse(date);
			  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
		 return parseDate;  
		   
		   
	   }
	   
	   
	   
	  public static void main(String[] args) {
		  
		  getTime(1, "2013-8-22");
		
	}   
	  //计算发布时间
	  public static String getTimer(Timestamp timestamp ){
		long mimus1=new Date().getTime()-timestamp.getTime();
		int cha=(int)mimus1/1000/60;
		String content="";
	  	int hourse=cha/60;
	       if(hourse>1&&hourse<24){
	    	   content=hourse+"小时以前";
	      }
	       else if(hourse<1){
	    	   content=cha+"分钟以前";
	    	   if(cha==0){
	    		   content="刚刚"; 
	    		   
	    	   }
	       }
	       else if(hourse>24&&hourse<48){
	    	   content="昨天";    
	       }else{  	   
	    	   content=new SimpleDateFormat("yyyy/MM/dd").format(timestamp);     
	       }
		
		return content;
	  }
	  
	  
	  public static String formatString(Object number){
		  DecimalFormat decimalFormat=new DecimalFormat("#.##");
		  return decimalFormat.format(number);
	  }
	  SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  /**
	     * 当前月的开始时间，即2012-01-31 23:59:59
	     * 
	     * @return
	     */
	  public   Date getCurrentMonthStartTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.DATE, 1);
	            now = longSdf.parse(longSdf.format(c.getTime()));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }

	    /**
	     * 当前月的结束时间，即2012-01-31 23:59:59
	     * 
	     * @return
	     */
	    public   Date getCurrentMonthEndTime() {
	        Calendar c = Calendar.getInstance();
	        Date now = null;
	        try {
	            c.set(Calendar.DATE, 1);
	            c.add(Calendar.MONTH, 1);
	            c.add(Calendar.DATE, -1);
	            now = longSdf.parse(longSdf.format(c.getTime()) + " 23:59:59");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return now;
	    }
	  
	  
	  
	
	  public static int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }


}
