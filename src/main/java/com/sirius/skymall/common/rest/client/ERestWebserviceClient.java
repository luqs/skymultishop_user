package com.sirius.skymall.common.rest.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class ERestWebserviceClient {
	  public final static String METHOD_GET="GET";  
      public final static String METHOD_PUT="PUT";  
      public final static String METHOD_DELETE="DELETE";  
      public final static String METHOD_POST="POST";  
        
      public static JSONObject rest(String serviceUrl,String parameter,String restMethod){  
          try {  
                URL url= new URL(serviceUrl);  
                HttpURLConnection con = (HttpURLConnection)url.openConnection();  
                con.setRequestMethod(restMethod);  
            	con.setRequestProperty("Content-type", "application/json");
            	con.setRequestProperty("Charset", "utf-8"); 
                //如果请求方法为PUT,POST和DELETE设置DoOutput为真  
                if(!ERestWebserviceClient.METHOD_GET.equals(restMethod)){  
                    con.setDoOutput(true);  
                    if(!ERestWebserviceClient.METHOD_DELETE.equals(restMethod)){ //请求方法为PUT或POST时执行  
                        OutputStream os = con.getOutputStream();   
                        os.write(parameter.getBytes("UTF-8"));   
                        os.close();   
                    }  
                    ////
                    
                    ////
                }  
//                else{ //请求方法为GET时执行  
                    StringBuffer buffer = new StringBuffer();
                    InputStream in= con.getInputStream();                
                    byte[] b= new byte[1024];  
                    int result= in.read(b);  
                    while( result!=-1){  
                        buffer.append(new String(b, 0, result,"UTF-8"));
                        result= in.read(b);  
                    }  
                	System.out.println(buffer);
                    if (con.getResponseCode() != 200) {
                    	return null;
                    }
    			JSONObject obj = new JSONObject().fromObject(buffer.substring(10,buffer.length() - 1));
    			return obj;
          } catch ( Exception e ) {  
        	  e.printStackTrace();
              throw new RuntimeException(e );  
          }  
      }  
      
      
      
      
      public static JSONObject  rest(String serviceUrl,String restMethod){  
          try {  
                URL url= new URL(serviceUrl);  
                HttpURLConnection con = (HttpURLConnection)url.openConnection();  
                con.setRequestMethod(restMethod);  
            	con.setRequestProperty("Content-type", "application/json");
            	con.setRequestProperty("Charset", "utf-8"); 
                //如果请求方法为PUT,POST和DELETE设置DoOutput为真  
                if(!ERestWebserviceClient.METHOD_GET.equals(restMethod)){  
                    con.setDoOutput(true);  
                    if(!ERestWebserviceClient.METHOD_DELETE.equals(restMethod)){ //请求方法为PUT或POST时执行  
                        OutputStream os = con.getOutputStream();   
                        //os.write(parameter.getBytes("UTF-8"));   
                        os.close();   
                    }  
                    ////
                    
                    ////
                }  
//                else{ //请求方法为GET时执行  
                    StringBuffer buffer = new StringBuffer();
                    InputStream in= con.getInputStream();                
                    byte[] b= new byte[1024];  
                    int result= in.read(b);  
                    while( result!=-1){  
                        buffer.append(new String(b, 0, result,"UTF-8"));
                        result= in.read(b);  
                    }  
                	System.out.println(buffer);
                    if (con.getResponseCode() != 200) {
                    	return null;
                    }
    			JSONObject obj = new JSONObject().fromObject(buffer.toString());
    			return obj;
          } catch ( Exception e ) {  
        	  e.printStackTrace();
              throw new RuntimeException(e );  
          }  
      }  
      
      

      public static void main(String args[]){  

//        //GET  
//        rest("http://localhost:8081/sqlrest/PRODUCT/4",null,WeiboRestClient.METHOD_GET);  
//          
//        //PUT  
//        String put="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><PRODUCT xmlns:xlink=\"http://www.w3.org/1999/xlink\"><NAME>Chair Shoe</NAME>"  
//  +"<PRICE>24.8</PRICE></PRODUCT>";  
//        rest("http://localhost:8081/sqlrest/PRODUCT/395",put,WeiboRestClient.METHOD_PUT);  
//          
//    	  RgisterParam param = new RgisterParam();
//    	  param.setName("ooook");
//    	  param.setBusinesslicence("assssssss");
//    	  param.setIdentitycard("asdhfkjashdjkas");
//    	  param.setNewpwd("12233");
//    	  param.setPwd("12233");
//    	  param.setLoginname("gongwei");
//    	  JSONObject obj = new JSONObject().fromObject(param);
//    	  String strParam ="{\"BusinessUserEntity\":"+obj.toString()+"}";
//    	  System.out.println(strParam);
//        //POST  
//       String post="{\"BusinessUserEntity\":{\"name\":\"s\",\"pwd\":\"asa\",\"newpwd\":\"asa\",\"identitycard\":\"hhh\",\"businesslicence\":\"saa\"}}";
//
//       JSONObject obj2 = rest("http://172.16.237.108:8080/user/service/rest/businessUser/register",strParam,ERestWebserviceClient.METHOD_POST);  
//       RgisterResult rs = (RgisterResult) JSONObject.toBean(obj2, RgisterResult.class);
//       System.out.println("---"+rs.getErrorMessage()+""+rs.getBusniessUser().getBusinesslicence());

//        //DELETE  
//        rest("http://localhost:8081/sqlrest/PRODUCT/395",null,WeiboRestClient.METHOD_DELETE);
    	  
    	   JSONObject jsonObject=rest("http://192.168.1.129:8080/user/service/rest/user/getBusinessUser/18", ERestWebserviceClient.METHOD_GET);  
    	   Result rgisterResult=(Result)JSONObject.toBean(jsonObject, Result.class);
    	   System.out.println("======================");
    
      }  
}
