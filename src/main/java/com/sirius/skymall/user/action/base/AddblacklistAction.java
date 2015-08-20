package com.sirius.skymall.user.action.base;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.Blacklist;
import com.sirius.skymall.user.model.base.SessionInfo;
import com.sirius.skymall.user.model.easyui.Grid;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.SyblacklistService;
import com.sirius.skymall.user.util.base.ConfigUtil;
import com.sirius.skymall.user.util.base.HqlFilter;

@Namespace("/base")
@Action
public class AddblacklistAction extends BaseAction<Blacklist> {
	private String loginName;//登录名
	private String regBeginDate;//注册起始时间
	private String regEndDate;//注册结束时间
	private String tabname;
	@Autowired
	public void setService(SyblacklistService service) {
		this.service=service;
	}
	public void grid(){
		try{
			System.out.println("+++++++++++++++++++++++++++$$$$$$$$$$$$$$$$$$$$$$");
			System.out.println("登录名："+this.getLoginName()+";注册时间从 "+this.getRegBeginDate()+" 到 "+this.getRegEndDate());
			Grid grid=new Grid();
			HqlFilter hqlFilter=new HqlFilter(getRequest());
			SessionInfo sessionInfo=(SessionInfo)getSession().getAttribute(ConfigUtil.getSessionInfoName());
			BigInteger bi=((SyblacklistService)service).countAllUsersByFilter(hqlFilter,this.getLoginName(),this.getRegBeginDate(),this.getRegEndDate());
			Long l=bi.longValue();
			grid.setTotal(l);
			List result =((SyblacklistService)service).findAllUsersByFilter(hqlFilter, page, rows,this.getLoginName(),this.getRegBeginDate(),this.getRegEndDate());
			grid.setRows(result);
		    writeJson(grid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 加入到黑名单
	 */
	public void black(){
		try{
			Json json = new Json();
			System.out.println("@@@@@@@@@@@@@@加入到黑名单的ID："+id);
			if(id!=null && id.trim().length()>0){
				if(id.contains("%")){
					String[] idAndTable=id.split("%");
					if(idAndTable!=null && idAndTable.length>0){
						String userId=idAndTable[0];
						String tableName=idAndTable[1];
						//将对应的用户插入到黑名单表
						List result=((SyblacklistService)service).findUsrById(userId,tableName);
						if(result!=null && result.size()>0){
							Map map=(Map)result.get(0);
							Blacklist bl=new Blacklist();
							bl.setAge(map.get("age")!=null?Integer.parseInt(map.get("age").toString()):null);
							bl.setCreateDate(new Date());
							bl.setLoginname(map.get("loginname")!=null?map.get("loginname").toString():null);
							bl.setName(map.get("name")!=null?map.get("name").toString():null);
							bl.setSex(map.get("sex")!=null?Integer.parseInt(map.get("sex").toString()):null);
							bl.setUserId(Integer.parseInt(userId));
							bl.setUserIntablename(tableName);
							service.save(bl);
							//再将对应的用户表更新
							int updateResult=((SyblacklistService)service).updateUser(userId,tableName);
							if(updateResult>0){
								json.setSuccess(true);
							}
						}
					}
				}
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRegBeginDate() {
		return regBeginDate;
	}
	public void setRegBeginDate(String regBeginDate) {
		this.regBeginDate = regBeginDate;
	}
	public String getRegEndDate() {
		return regEndDate;
	}
	public void setRegEndDate(String regEndDate) {
		this.regEndDate = regEndDate;
	}
	public String getTabname() {
		return tabname;
	}
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	
	
	
}
