package com.sirius.skymall.user.ws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	public static Date String2Date(String sDate){
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(sDate);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
}
