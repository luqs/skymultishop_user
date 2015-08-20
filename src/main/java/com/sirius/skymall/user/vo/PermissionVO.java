package com.sirius.skymall.user.vo;

public class PermissionVO {
	private boolean haveGetPermission;
	private boolean haveUpdatePermission;
	private boolean haveDelPermission;
	private boolean haveSavePermission;
	private boolean haveGrantPermission;
	private boolean haveCleanPermission;
	private boolean haveImportPermission;
	private boolean haveResetPwdPermission;
	
	public boolean isHaveResetPwdPermission() {
		return haveResetPwdPermission;
	}
	public void setHaveResetPwdPermission(boolean haveResetPwdPermission) {
		this.haveResetPwdPermission = haveResetPwdPermission;
	}
	public boolean isHaveCleanPermission() {
		return haveCleanPermission;
	}
	public void setHaveCleanPermission(boolean haveCleanPermission) {
		this.haveCleanPermission = haveCleanPermission;
	}
	public boolean isHaveImportPermission() {
		return haveImportPermission;
	}
	public void setHaveImportPermission(boolean haveImportPermission) {
		this.haveImportPermission = haveImportPermission;
	}
	public boolean isHaveGrantPermission() {
		return haveGrantPermission;
	}
	public void setHaveGrantPermission(boolean haveGrantPermission) {
		this.haveGrantPermission = haveGrantPermission;
	}
	public boolean isHaveGetPermission() {
		return haveGetPermission;
	}
	public void setHaveGetPermission(boolean haveGetPermission) {
		this.haveGetPermission = haveGetPermission;
	}
	public boolean isHaveUpdatePermission() {
		return haveUpdatePermission;
	}
	public void setHaveUpdatePermission(boolean haveUpdatePermission) {
		this.haveUpdatePermission = haveUpdatePermission;
	}
	public boolean isHaveDelPermission() {
		return haveDelPermission;
	}
	public void setHaveDelPermission(boolean haveDelPermission) {
		this.haveDelPermission = haveDelPermission;
	}
	public boolean isHaveSavePermission() {
		return haveSavePermission;
	}
	public void setHaveSavePermission(boolean haveSavePermission) {
		this.haveSavePermission = haveSavePermission;
	}
	
}
