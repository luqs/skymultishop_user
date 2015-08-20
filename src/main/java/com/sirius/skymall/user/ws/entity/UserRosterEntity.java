package com.sirius.skymall.user.ws.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserRosterEntity")
public class UserRosterEntity {
	private String username;
	private String friendUsername;
	private Boolean roomNumBeWatch;
	private Boolean roomNumCanWatch;
	
	public Boolean getRoomNumCanWatch() {
		return roomNumCanWatch;
	}

	public void setRoomNumCanWatch(Boolean roomNumCanWatch) {
		this.roomNumCanWatch = roomNumCanWatch;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFriendUsername() {
		return friendUsername;
	}

	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}

	public Boolean getRoomNumBeWatch() {
		return roomNumBeWatch;
	}

	public void setRoomNumBeWatch(Boolean roomNumBeWatch) {
		this.roomNumBeWatch = roomNumBeWatch;
	}


}
