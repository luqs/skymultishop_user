package com.sirius.skymall.user.service.base.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.UserRoster;
import com.sirius.skymall.user.service.base.UserRosterService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.skysea.pushing.api.EventPublisher;
import com.skysea.pushing.api.PublishException;
import com.skysea.pushing.api.PushInfrastructure;
import com.skysea.pushing.xmpp.XMPPPushInfrastructure;

@Service
public class UserRosterServiceImpl extends BaseServiceImpl<UserRoster> implements UserRosterService {

	@Override
	public List<UserRoster> getRoomNumInfList(String username) {
		String hql="from UserRoster where friendUsername='"+username+"' or username='"+username+"'";
		List<UserRoster> list=this.find(hql);
		return list;
	}

	@Override
	public void updateRoomNumInf(UserRoster userRoster) {
		String hql="from UserRoster where friendUsername='"+userRoster.getFriendUsername()+"' and username='"+userRoster.getUsername()+"'";
		List<UserRoster> list=this.find(hql);
		if(list==null||list.size()<=0){
			this.save(userRoster);
		}else{
			PushInfrastructure factory = new XMPPPushInfrastructure(
	        		"skysea.com",    /* xmppdomain*/
	        		"http://im.skysea.com:9090/plugins/push/packet"     /* pushGatewayUrl */);
			
			EventPublisher eventPublisher = factory.getEventPublisher();
			
			for(UserRoster entity:list){
				entity.setRoomNumBeWatch(userRoster.getRoomNumBeWatch());
				entity.setUpdateTime(userRoster.getUpdateTime());
				this.update(entity);
				try {
					HashMap<String, String> sche = new HashMap<String, String>();
					sche.put("friendname", entity.getUsername());
					sche.put("allowwatch", String.valueOf(entity.getRoomNumBeWatch()));
					eventPublisher.publish(entity.getFriendUsername(),"roomnum_bewatch", sche);
				} catch (PublishException e) {
				
				}
			}
			
			
		}
	}

}
