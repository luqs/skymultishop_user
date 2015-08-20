package com.sirius.skymall.user.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.model.base.Survey;
import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.model.base.SurveyQuestion;
import com.sirius.skymall.user.model.base.SurveyQuestionItem;
import com.sirius.skymall.user.model.base.UserSurvey;
import com.sirius.skymall.user.model.base.UserSurveySetting;
import com.sirius.skymall.user.model.base.VoyageInfo;
import com.sirius.skymall.user.service.base.SurveyAnswerService;
import com.sirius.skymall.user.service.base.SurveyQuestionItemService;
import com.sirius.skymall.user.service.base.SurveyQuestionService;
import com.sirius.skymall.user.service.base.SurveyService;
import com.sirius.skymall.user.service.base.UserSurveyService;
import com.sirius.skymall.user.service.base.UserSurveySettingService;
import com.sirius.skymall.user.service.base.VoyageInfoService;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;
import com.sirius.skymall.user.util.base.QuestionTypeEnum;
import com.sirius.skymall.user.ws.SurveyWebService;
import com.sirius.skymall.user.ws.entity.QuestionEntity;
import com.sirius.skymall.user.ws.entity.QuestionParam;
import com.sirius.skymall.user.ws.entity.SurveyParam;
import com.sirius.skymall.user.ws.entity.SurveyQueryCondition;
import com.sirius.skymall.user.ws.entity.SurveyQuestionItemEntity;
import com.sirius.skymall.user.ws.error.ValidationError;
import com.sirius.skymall.user.ws.result.SurveyCommitResult;
import com.sirius.skymall.user.ws.result.SurveyResult;

public class SurveyWebServiceImpl extends BaseServiceImpl<Survey>  implements SurveyWebService{

	@Autowired
	SurveyService surveyService;
	@Autowired
	SurveyQuestionService surveyQuestionService;
	@Autowired
	SurveyAnswerService surveyAnswerService;
	@Autowired
	SurveyQuestionItemService surveyQuestionItemService;
	@Autowired
	UserSurveyService userSurveyService;
	@Autowired
	UserSurveySettingService userSurveySettingService;
	
	@Autowired
	VoyageInfoService voyageInfoService;
	
	private static final Logger logger = Logger.getLogger(SurveyWebServiceImpl.class);

	@Override
	public SurveyResult getQuestions(SurveyQueryCondition condition) {
		SurveyResult sr=new SurveyResult();
		try {
			List<Survey> surveies = surveyService.find("from Survey");
			if(surveies!=null && surveies.size()>0){
				Survey survey = surveies.get(0);
				List<SurveyQuestion> questions = surveyQuestionService.find("from SurveyQuestion where surveyId="+survey.getId(), condition.getPageNumber(), condition.getPageSize());
				Long total = surveyQuestionService.count("select count(*) from SurveyQuestion where surveyId="+survey.getId());
				if(questions!=null && questions.size()>0){
					List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
					for(int i=0;i<questions.size();i++){
						SurveyQuestion question = questions.get(i);
						QuestionEntity entity = new QuestionEntity();
						entity.setId(question.getId());
						entity.setQuestion(question.getQuestion());
						entity.setSurveyId(question.getSurveyId());
						entity.setType(question.getType());
						entity.setCreatedatetime(question.getCreatedatetime());
						entity.setUpdatedatetime(question.getUpdatedatetime());
						List<SurveyAnswer> qAnswers = surveyAnswerService.find("from SurveyAnswer where questionId="+question.getId());
						entity.setAnswers(qAnswers);
						entity.setIndex((condition.getPageNumber()-1)*condition.getPageSize()+i+1);
						List<SurveyQuestionItem> qItems = surveyQuestionItemService.find("from SurveyQuestionItem where questionId="+question.getId());
						entity.setItems(qItems);
						if(new Long(condition.getPageNumber()).equals(total)){
							entity.setIsLasted(true);
						}else{
							entity.setIsLasted(false);
						}
						questionEntities.add(entity);
					}
					sr.setErrorCode(0);
					sr.setErrorMessage("");
					sr.setQuestions(questionEntities);
				}
			}
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			sr.setErrorCode(errorCode);
			sr.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return sr;
	}

	@Override
	public SurveyCommitResult commit(SurveyParam param) {
		SurveyCommitResult sr=new SurveyCommitResult();
		try {
			String voyageId="";
			List<VoyageInfo> voyageInfos=voyageInfoService.find();
			if(voyageInfos!=null&&voyageInfos.size()>0){
				voyageId=voyageInfos.get(0).getVoyageId();
			}
			if(param.getQuestions()!=null && param.getQuestions().size()>0){
				for(QuestionParam question:param.getQuestions()){
					List<SurveyQuestionItemEntity> items = question.getItems();
					if(items!=null && items.size()>0){
						for(SurveyQuestionItemEntity itemEntity:items){
							UserSurvey us = new UserSurvey();
							us.setUserId(param.getUserId());
							us.setQuestionId(question.getId());
							us.setItemId(itemEntity.getId());
							if(question.getType()==QuestionTypeEnum.SINGLE.getValue()||question.getType()==QuestionTypeEnum.ONECHOICE.getValue()){
								us.setAnswerId(itemEntity.getAnswerId());
							}else if(question.getType()==QuestionTypeEnum.ASK.getValue()){
								us.setAnswer(itemEntity.getAnswer());
							}
							us.setCreatedatetime(new Date(System.currentTimeMillis()));
							us.setUpdatedatetime(new Date(System.currentTimeMillis()));
							us.setVoyageId(voyageId);
							userSurveyService.save(us);
						}
					}else{
						if(question.getType()==QuestionTypeEnum.SINGLE.getValue() || question.getType()==QuestionTypeEnum.ONECHOICE.getValue()){
							UserSurvey us = new UserSurvey();
							us.setUserId(param.getUserId());
							us.setQuestionId(question.getId());
							us.setAnswerId(question.getAnswerId());
							us.setCreatedatetime(new Date(System.currentTimeMillis()));
							us.setUpdatedatetime(new Date(System.currentTimeMillis()));
							us.setVoyageId(voyageId);
							userSurveyService.save(us);
							
						}else if(question.getType()==QuestionTypeEnum.MULTI.getValue()){
							if(question.getAnswerIds()!=null){
								for(int i=0;i<question.getAnswerIds().length;i++){
									UserSurvey us = new UserSurvey();
									us.setUserId(param.getUserId());
									us.setQuestionId(question.getId());
									us.setAnswerId(question.getAnswerIds()[i]);
									us.setCreatedatetime(new Date(System.currentTimeMillis()));
									us.setUpdatedatetime(new Date(System.currentTimeMillis()));
									us.setVoyageId(voyageId);
									userSurveyService.save(us);
								}
							}
						}else if(question.getType()==QuestionTypeEnum.ASK.getValue()){
							UserSurvey us = new UserSurvey();
							us.setUserId(param.getUserId());
							us.setQuestionId(question.getId());
							us.setAnswer(question.getAnswer());
							us.setCreatedatetime(new Date(System.currentTimeMillis()));
							us.setUpdatedatetime(new Date(System.currentTimeMillis()));
							us.setVoyageId(voyageId);
							userSurveyService.save(us);
						}
					}
				}
				UserSurveySetting userSetting = new UserSurveySetting();
				userSetting.setUserId(param.getUserId());
				userSetting.setAcceptEmail(param.getAcceptEmail());
				userSetting.setAcceptAnalyst(param.getAcceptAnalyst());
				userSetting.setAcceptAds(param.getAcceptAds());
				userSetting.setCreatedatetime(new Date(System.currentTimeMillis()));
				userSetting.setUpdatedatetime(new Date(System.currentTimeMillis()));
				userSurveySettingService.save(userSetting);
				sr.setErrorCode(0);
				sr.setErrorMessage("");
			}
		} catch (Exception e) {
			ValidationError er=ValidationError.SYSTEM_ERROR;
			int errorCode=er.getErrorCode();
			sr.setErrorCode(errorCode);
			sr.setErrorMessage("系统错误");
			logger.error(e.getMessage());
		}
		return sr;
	}
	
	
}
