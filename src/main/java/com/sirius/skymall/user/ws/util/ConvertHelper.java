package com.sirius.skymall.user.ws.util;

import java.util.Date;

import com.sirius.skymall.user.model.base.BusinessUserDetail;
import com.sirius.skymall.user.model.base.Message;
import com.sirius.skymall.user.model.base.User;
import com.sirius.skymall.user.model.base.UserAddress;
import com.sirius.skymall.user.model.base.UserDetail;
import com.sirius.skymall.user.model.base.UserRoster;
import com.sirius.skymall.user.ws.entity.BusinessUserEntity;
import com.sirius.skymall.user.ws.entity.MessageEntity;
import com.sirius.skymall.user.ws.entity.MessageTypeEnum;
import com.sirius.skymall.user.ws.entity.UserAddressEntity;
import com.sirius.skymall.user.ws.entity.UserEntity;
import com.sirius.skymall.user.ws.entity.UserRosterEntity;

public class ConvertHelper {
	public static UserEntity toUserEntity(User u,UserDetail userdetail){
		UserEntity userVO = new UserEntity();
    	userVO.setId(u.getId().toString());
    	userVO.setLoginname(u.getLoginname());
    	userVO.setName(u.getName());
    	userVO.setRealname(u.getRealname());
    	userVO.setPhone(u.getPhone());
    	userVO.setEmail(u.getEmail());
    	userVO.setBoatcard(u.getBoatcard());
    	userVO.setUsertype(u.getUsertype());
    	userVO.setState(Constants.DEFAULT_STATE);
    	userVO.setCity(Constants.DEFAULT_CITY);
    	userVO.setCansearch(u.getCansearch()==null?0:u.getCansearch());
    	userVO.setCansearchbyphone(u.getCansearchbyphone()==null?0:u.getCansearchbyphone());
    	userVO.setHideroomtelephone(u.getHideroomtelephone()==null?1:u.getHideroomtelephone());
    	userVO.setPinyinname(u.getPinyinname());
    	//userVO.setSex(Constants.DEFAULT_SEX_VALUE);
//    	if(u.getHideroomtelephone()==null || (u.getHideroomtelephone()!=null &&u.getHideroomtelephone()==0)){
//    		if(u.getRoomtelephone()!=null){
    			userVO.setRoomtelephone(u.getRoomtelephone());
//    		}
//    	}
    	if(userdetail!=null){
    		if(userdetail.getSex()!=null){
    			//userVO.setSex(userdetail.getSex()==1?"男":"女");
    			userVO.setSex(userdetail.getSex().toString());
    		}
    		if(userdetail.getAge()!=null){
    			userVO.setAge(userdetail.getAge());
    		}
        	if(userdetail.getCountry()!=null){
        		userVO.setCountry(userdetail.getCountry());
        	}
        	if(userdetail.getState()!=null){
        		userVO.setState(userdetail.getState());
        	}
        	if(userdetail.getCity()!=null){
        		userVO.setCity(userdetail.getCity());
        	}
        	if(userdetail.getPhoto()!=null){
        		userVO.setPhoto(userdetail.getPhoto());
        	}
        	if(userdetail.getConstellation()!=null){
        		userVO.setConstellation(userdetail.getConstellation());
        	}
        	if(userdetail.getSignature()!=null){
        		userVO.setSignature(userdetail.getSignature());
        	}
        	if(userdetail.getHobby()!=null){
        		userVO.setHobby(userdetail.getHobby());
        	}
        	if(userdetail.getRoom()!=null){
        		userVO.setRoom(userdetail.getRoom());
        	}
    	}
    	return userVO;
	}
	public static BusinessUserEntity toBusinessUserEntity(User u,BusinessUserDetail bud){
		BusinessUserEntity userVO = new BusinessUserEntity();
    	userVO.setId(u.getId().toString());
    	userVO.setLoginname(u.getLoginname());
    	userVO.setName(u.getName());
    	userVO.setRealname(u.getRealname());
    	userVO.setPhone(u.getPhone());
    	userVO.setStatus(u.getStatus());
    	userVO.setUsertype(u.getUsertype());
    	if(bud!=null){
    		userVO.setScope(bud.getScope());
        	userVO.setAddress(bud.getAddress());
        	userVO.setContactor(bud.getContactor());
        	userVO.setIdentitycard(bud.getIdentitycard());
        	userVO.setBusinesslicence(bud.getBusinesslicence());
        	userVO.setZuoji(bud.getZuoji());
    	}
    	return userVO;
	}
	public static UserAddress toUserAddress(UserAddressEntity entity){
		UserAddress useraddress = new UserAddress();
		useraddress.setName(entity.getName());
		useraddress.setPhone(entity.getPhone());
		String postcode = entity.getPostcode();
		if(postcode!=null){
			useraddress.setPostcode(postcode);
		}
		Double lat = entity.getLat();
		Double lon = entity.getLon();
		if(lat!=null){
			useraddress.setLat(lat);
		}
		if(lon!=null){
			useraddress.setLon(lon);
		}
		useraddress.setAddress(entity.getAddress());
		useraddress.setCreateTime(new Date());
		useraddress.setUpdateTime(new Date());
		return useraddress;
	}
	public static UserAddressEntity toUserAddressEntity(UserAddress address){
		UserAddressEntity entity = new UserAddressEntity();
		entity.setId(address.getId());
		entity.setUid(address.getUid());
		entity.setName(address.getName());
		entity.setPhone(address.getPhone());
		entity.setAddress(address.getAddress());
		Double lat = address.getLat();
		Double lon = address.getLon();
		if(lat!=null){
			entity.setLat(lat);
		}
		if(lon!=null){
			entity.setLon(lon);
		}
		String postcode = address.getPostcode();
		if(postcode!=null){
			entity.setPostcode(postcode);
		}
		Integer addresstype = address.getAddresstype();
		if(addresstype!=null){
			entity.setAddresstype(addresstype);
		}
		
		return entity;
	}
	public static MessageEntity toMessageEntity(Message msg){
		MessageEntity msgEntity = new MessageEntity();
		msgEntity.setBusinessId(msg.getBusinessId());
		msgEntity.setToUserId(msg.getToUserId());
		msgEntity.setMsgType(msg.getMsgType());
		if(msg.getMsgType()==MessageTypeEnum.ORDER.getCode()){
			msgEntity.setMsgTypeDes(MessageTypeEnum.ORDER.getName());
		}else if(msg.getMsgType()==MessageTypeEnum.DELIVER.getCode()){
			msgEntity.setMsgTypeDes(MessageTypeEnum.DELIVER.getName());
		}
		msgEntity.setFromUserId(msg.getFromUserId());
		msgEntity.setId(msg.getId());
		msgEntity.setUpdatedatetime(msg.getUpdatedatetime().toString());
		msgEntity.setMsgContent(msg.getMsgContent());
		msgEntity.setCreatedatetime(msg.getCreatedatetime().toString());
		return msgEntity;
	}
	public static Message toMessage(MessageEntity message){
		String businessId = message.getBusinessId();
		Integer fromUserId = message.getFromUserId();
		Integer toUserId = message.getToUserId();
		Message msg = new Message();
		msg.setBusinessId(businessId);
		msg.setFromUserId(fromUserId);
		msg.setToUserId(toUserId);
		msg.setMsgType(message.getMsgType());
		msg.setMsgContent(message.getMsgContent());
		return msg;
	}
	
	public static UserRosterEntity toUserRosterEntity(UserRoster userRoster){
		UserRosterEntity userRosterEntity = new UserRosterEntity();
		userRosterEntity.setUsername(userRoster.getUsername());
		userRosterEntity.setFriendUsername(userRoster.getFriendUsername());
		userRosterEntity.setRoomNumBeWatch(userRoster.getRoomNumBeWatch());
		return userRosterEntity;
	}
}
