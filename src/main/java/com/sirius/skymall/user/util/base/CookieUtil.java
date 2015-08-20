package com.sirius.skymall.user.util.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
      private final static String cookieDomainName = "token";
       public void saveCookie(String value, HttpServletResponse response){
    	   
    	   Cookie cookie=new Cookie(cookieDomainName, value);
    	   //设置cookie的有效期为李兰器内存,浏览器关闭cookie消失
    	   cookie.setMaxAge(-100);
    	   cookie.setPath("/");
    	   response.addCookie(cookie);   
       }
       
       
       public static String readCookie(HttpServletRequest request){
           
       	Cookie cookies[] = request.getCookies();
   		String cookieValue = null;
   		if(cookies!=null){
   			for(int i=0;i<cookies.length;i++){
   				if (cookieDomainName.equals(cookies[i].getName())) {
   					cookieValue = cookies[i].getValue();
   					break;
   				}
   			}
   		}

   		return  cookieValue;
          }
	
	public void removeCookies(HttpServletResponse response){
	   Cookie cookie=new Cookie(cookieDomainName, null);
  	   cookie.setMaxAge(0);
  	   cookie.setPath("/");
  	   response.addCookie(cookie);   

	}

}
