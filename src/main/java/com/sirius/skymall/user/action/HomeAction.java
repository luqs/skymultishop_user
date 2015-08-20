package com.sirius.skymall.user.action;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


@SuppressWarnings("rawtypes")
public class HomeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5635719929986026253L;
	private static final String CONST_ADMIN_MAIN = "adminMain";
	 public String doNotNeedSessionAndSecurity_execute() {
		 HttpSession session = getSession();
		 if(session!=null){
			 SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
			 if (sessionInfo != null) {
				 return CONST_ADMIN_MAIN;
			 }else{
				 return LOGIN;
			 }
			 
		 }else{
			 return LOGIN;
		 }
		
		
	 }
	 
		//登陆验证码
		private int width=57;//图像宽度
		private int height=21;//图像高度 
		public String doNotNeedSessionAndSecurity_validateCode() throws Exception{
			this.getResponse().setContentType("image/jpeg");//定义输出格式
			ServletOutputStream out = this.getResponse().getOutputStream();
			BufferedImage bimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);//准备缓冲图像,不支持表单
			Random random = new Random();
			//获取图形上下文环境
			Graphics gc = bimg.getGraphics();
			//设定背景色并进行填充
			gc.setColor(getRandColor(200, 250));
			gc.fillRect(0, 0, width, height);
			//设置图形上下文环境字体
			gc.setFont(new Font("Times New Roman",Font.PLAIN,18));
			//随机产生200条干扰线条，使图像中的认证码不易被其他分析程序探测到
			gc.setColor(getRandColor(160, 200));
			for(int i=0;i<200;i++){
				int x1 = random.nextInt(width);
				int y1 = random.nextInt(height);
				int x2 = random.nextInt(15);
				int y2 = random.nextInt(15);
				gc.drawLine(x1, y1, x1+x2,y1+y2 );
			}
			//随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测到
			gc.setColor(getRandColor(120, 240));
			for(int i=0;i<100;i++){
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				gc.drawOval(x, y, 0, 0);
			}
			//随机产生4个数字的验证码
			String rs="";
			String rn="";
			for(int i=0;i<4;i++){
				rn = String.valueOf(random.nextInt(10));
				rs+=rn;
				gc.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
				gc.drawString(rn, 13*i+1, 16);
			}
			//释放图形上下文环境
			gc.dispose();
			//setSession("loginValidateCode", rs);
			super.getSession().setAttribute("loginValidateCode", rs);
			JPEGImageEncoder codee = JPEGCodec.createJPEGEncoder(out);
			codee.encode(bimg);
			out.flush();
			out.close();
			return null;
		}
		public Color getRandColor(int fc,int bc){
			Random random = new Random();
			if(fc>255) fc = 255;
			if(bc>255) bc = 255;
			int red = fc+random.nextInt(bc - fc);
			int green = fc+random.nextInt(bc - fc);
			int blue = fc+random.nextInt(bc -fc);
			return new Color(red,green,blue);
		}
		

}
