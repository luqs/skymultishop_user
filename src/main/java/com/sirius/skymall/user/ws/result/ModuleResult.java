package com.sirius.skymall.user.ws.result;



import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sirius.skymall.user.model.base.ModuleSetting;


@XmlRootElement(name="Result")
public class ModuleResult extends ApiBaseResult{
	private List<ModuleSetting> modules;
	private String homehtml="";
	
	
	
	public String getHomehtml() {
		return homehtml;
	}
	public void setHomehtml(String homehtml) {
		this.homehtml = homehtml;
	}
	public ModuleResult(){
	}
	public List<ModuleSetting> getModules() {
		return modules;
	}
	public void setModules(List<ModuleSetting> modules) {
		this.modules = modules;
	}
	public ModuleResult(int errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
