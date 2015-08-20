package com.sirius.skymall.user.util.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sirius.skymall.user.model.base.ModuleSetting;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class StaticCommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7983951648608176592L;
	/**
	 * 
	 */
	private static final String templatePath = "static/tpl";
	//private static final String targetHtmlPath= "publish/home.html";
	//private static final String targetHtmlPath= "publish/productHome.html";
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
	private static Map<String,String> map=null; 
	
	
	
	public static String getCacahhtml(){
		
		return getmap().get("home");
	}
	public static void setCacahhtml(String html){
		getmap().put("home", html);
	}
	public static Map<String,String> getmap(){
		if(map==null){
			map=new HashMap<String, String>();
			return map;
		}else{
			return map;
		}
	}
	
	
	public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
	
	public String readFile(String file_name){
		 try {
			 if(!new File(file_name).exists()){
				return null; 
			 }
			FileInputStream fin=new FileInputStream(file_name);
			int length=fin.available();
			byte[] buffer;
			buffer=new byte[length];
			fin.read(buffer);	
			String filetext=new String(buffer,"utf-8");
			setCacahhtml(filetext);
		    return filetext;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getPath(HttpServletRequest request) {
		
		return request.getSession().getServletContext().getRealPath("/static")+"/";
	}
	
	
	public String staticHome(List<ModuleSetting>  moduleSettings,ServletContext context,String version) {
		try {
			List<List<ModuleSetting>> moduleList=new ArrayList<List<ModuleSetting>>();
			if(moduleSettings!=null && moduleSettings.size()>3){
				for(int k=0;k<moduleSettings.size();k+=3){
					List<ModuleSetting> group =new ArrayList<ModuleSetting>();
					if(moduleSettings.get(k)!=null){
						group.add(moduleSettings.get(k));
					}
					if(moduleSettings.size()>=k+1&&moduleSettings.get(k+1)!=null){
						group.add(moduleSettings.get(k+1));
					}
					if(moduleSettings.size()-1>=k+2&&moduleSettings.get(k+2)!=null){
						group.add(moduleSettings.get(k+2));
					}
					moduleList.add(group);
				}
			}else{
				moduleList.add(moduleSettings);
			}  
			if(ConfigUtil.get("isdebug").equals("true")){
	            	version="";
	        }
			@SuppressWarnings("deprecation")
			String targetHtmlPath="";
			if(StringUtils.isNotBlank(version)){
				targetHtmlPath = ConfigUtil.get("targetHtmlPath")+File.separator+version+File.separator+"static/home.html";
			}else{
				targetHtmlPath = ConfigUtil.get("targetHtmlPath")+File.separator+"static/home.html";
			}
			String htmlPath=targetHtmlPath;
		 // FileUtils.deleteDir(new File(htmlPath));
			Configuration freemarkerCfg = new Configuration();
		  //加载模版
		  freemarkerCfg.setServletContextForTemplateLoading(context, "/");
		  freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		
		   //指定模版路径
		   Template template = freemarkerCfg.getTemplate(templatePath+"/home.html","UTF-8");
		   template.setEncoding("UTF-8");
		   //静态页面路径
		   File htmlFile = new File(htmlPath);
		   	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
		    //处理模
            Map<String,Object> datamap = new HashMap<String,Object>();
            datamap.put("moduleList", moduleList);
           
            datamap.put("version",version);
            template.process(datamap, out);
            out.flush();
            out.close();
            return htmlPath;
		  } catch (Exception e) {
		   e.printStackTrace();
		   return null;
		  } 
		
	}
}
