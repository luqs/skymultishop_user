package com.sirius.skymall.user.ws.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.CensorWords;
import com.sirius.skymall.user.service.base.AdminUserService;
import com.sirius.skymall.user.service.base.CensorWordsService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.ws.CensorwordsWsService;
import com.sirius.skymall.user.ws.entity.CensorwordsWs;
import com.sirius.skymall.user.ws.result.CensorwordsResult;

public class CensorwordsWsServiceImpl extends BaseServiceImpl<CensorWords> implements CensorwordsWsService {
	@Autowired
	private CensorWordsService censorWordsService;
	@Override
	public CensorwordsResult getall() {
		CensorwordsResult result=new CensorwordsResult();
		try{
		List<CensorWords> censorwords=censorWordsService.find();
		List<String> censorwordsws=new ArrayList<String>();//censorWordsService.find();
		for (CensorWords censorWords2 : censorwords) {
			censorwordsws.add(censorWords2.getWord());
		}
		
		result.setCensorwords(censorwordsws);
		result.setErrorCode(0);
		result.setErrorMessage("");
		}catch(Exception e){
			e.printStackTrace();
			result.setCensorwords(null);
			result.setErrorCode(-1);
			result.setErrorMessage("系统错误");
		}
		return result;
	}

	
}
