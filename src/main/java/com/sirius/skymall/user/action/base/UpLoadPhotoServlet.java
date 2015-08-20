package com.sirius.skymall.user.action.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.service.base.BusinessUserDetailService;
import com.sirius.skymall.user.service.base.UserDetailService;
import com.sirius.skymall.user.service.base.UserService;

public class UpLoadPhotoServlet extends HttpServlet{

	/**
	 * 上传用户头像功能
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UpLoadPhotoServlet.class);
	
	
	private void saveUserPhoto(String loginName,String photoPath,ServletContext sc){
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		UserService userService = (UserService)ac.getBean("userService");
		User user = userService.findByLoginName(loginName);
		if(user!=null){
			if(user.getUsertype()==2){
				BusinessUserDetailService bUserDetailService = (BusinessUserDetailService)ac.getBean("businessUserDetailService"); 
				BusinessUserDetail bDetail = bUserDetailService.getByHql("from BusinessUserDetail where userid="+user.getId());
				if(bDetail==null){
					bDetail = new BusinessUserDetail();
				}
				bDetail.setUserid(user.getId());
				bDetail.setPhoto(photoPath);
				bUserDetailService.saveOrUpdate(bDetail);
				
			}else{
				 UserDetailService userDetailService = (UserDetailService)ac.getBean("userDetailService"); 
				 UserDetail userDetail = userDetailService.getByHql("from UserDetail where userid="+user.getId());
				 if(userDetail==null){
					 userDetail = new UserDetail();
				 }
				 userDetail.setUserid(user.getId());
				 userDetail.setPhoto(photoPath);
				 userDetailService.saveOrUpdate(userDetail);
			}
		}
		
		
	 }
	 private  String getUUID(){
         return UUID.randomUUID().toString();
     }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 JSONObject json = new JSONObject();
		 try {
			 response.setCharacterEncoding("UTF-8");
             DiskFileItemFactory facotry = new DiskFileItemFactory();
             ServletFileUpload upload = new ServletFileUpload(facotry);
             upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码
             upload.setFileSizeMax(10*1024*1024);// 单个文件上传最大值是10M
             upload.setSizeMax(100*1024*1024);//文件上传的总大小限制100M

             //判断用户的表单提交方式是不是multipart/form-data
             boolean bb = upload.isMultipartContent(request);
             if (!bb) {
            	 json.put("status", "10001");
            	 json.put("message", "提交方式不是multipart");
            	 response.getWriter().write(json.toJSONString());
                 return;
             }
             //是：解析request对象的正文内容List<FileItem>
             List<FileItem> items = upload.parseRequest(request);
 			 String realPath= getServletContext().getRealPath("/");
 			 for(int i=0;i<=1;i++){
 				int lastXGPoint=realPath.lastIndexOf(File.separator);
 				realPath=realPath.substring(0, lastXGPoint);
 			 }
 			 String storePath = realPath+File.separator+"upload"+File.separator+"profile"+File.separator;
 			 File f=new File(storePath);
 			 if(!f.exists()){
 				f.mkdirs();
 			 }
 			 String path = f.getPath();
 			 if(path!=null && path.trim().length()>0){
 				path=path.replace("\\",f.separator);
 			 }
 			String webpath = "";
             for (FileItem item : items) {
                     if (item.isFormField()) {
                             //判断是否是普通表单：打印看看
                             String fieldName = item.getFieldName();// 请求参数名
                             String fieldValue = item.getString("UTF-8");// 请求参数值
                     } else {
                             //上传表单：得到输入流，处理上传：保存到服务器的某个目录中
                             String fileName = item.getName();
                             //解决用户没有选择文件上传的情况
                             if(fileName==null||fileName.trim().equals("")){
                                     continue;
                             }
                             fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                             String newFileName = getUUID() + "_" + fileName;
                             System.out.println("上传的文件名是：" + fileName);
                             InputStream in = item.getInputStream();
                             String savePath = path + f.separator + newFileName;
                             OutputStream out = new FileOutputStream(savePath);
                             byte b[] = new byte[1024];
                             int len = -1;
                             while ((len = in.read(b)) != -1) {
                                     out.write(b, 0, len);
                             }
                             in.close();
                             out.close();
                             item.delete();//删除临时文件
                             webpath="upload/profile/"+newFileName;
                             //saveToDB
                             String loginname = request.getParameter("loginname");
                             if(!StringUtils.isNullOrEmpty(loginname)){
                            	 saveUserPhoto(loginname,webpath, getServletContext());
                             }
                             
                     }
             }
             json.put("status", "0");
        	 json.put("message", "成功");
        	 json.put("picUrl", webpath);
        	 response.getWriter().write(json.toJSONString());
             return;
		     }catch(FileUploadBase.FileSizeLimitExceededException e){
	             logger.error("单个文件大小不能超出10M");
	             json.put("status", "10002");
	        	 json.put("message", "单个文件大小不能超出10M");
	             try {
	            	 response.getWriter().write(json.toJSONString());
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            
		     }catch(FileUploadBase.SizeLimitExceededException e){
	             logger.error("总文件大小不能超出100M");
	             json.put("status", "10003");
	        	 json.put("message", "总文件大小不能超出100M");
	        	 try {
	        		 response.getWriter().write(json.toJSONString());
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	           
			 }catch (Exception e) {
	             logger.error("上传失败");
	             json.put("status", "10004");
	        	 json.put("message", "上传失败");
	        	 try {
	        		 response.getWriter().write(json.toJSONString());
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			 }
	 }
}
