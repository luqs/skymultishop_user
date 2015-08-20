package com.sirius.skymall.user.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirius.skymall.user.action.BaseAction;
import com.sirius.skymall.user.model.base.SurveyAnswer;
import com.sirius.skymall.user.model.base.SurveyQuestion;
import com.sirius.skymall.user.model.base.SurveyQuestionItem;
import com.sirius.skymall.user.model.dto.SurveyQuestionDto;
import com.sirius.skymall.user.model.easyui.Json;
import com.sirius.skymall.user.service.base.SurveyAnswerService;
import com.sirius.skymall.user.service.base.SurveyQuestionItemService;
import com.sirius.skymall.user.service.base.SurveyQuestionService;
import com.sirius.skymall.user.util.base.BeanUtils;
import com.sirius.skymall.user.util.base.SecurityUtil;
import com.sirius.skymall.user.vo.PermissionVO;

/**
 * 问题管理
 * 
 * action访问地址是/base/question.sy
 * 
 * @author zzpeng
 * 
 */
@Namespace("/base")
@Action
public class QuestionAction extends BaseAction<SurveyQuestion> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1447524568362724059L;
	private static final String CONST_QUESTION = "question_home";
	private Integer surveyId;
	private String[] answers;
	private String[] items;
	private Integer type;
	private List<SurveyQuestionDto> questionDtos;
	@Autowired
	private SurveyQuestionService surveyService;
	
	@Autowired
	private SurveyAnswerService surveyAnswerService;
	@Autowired
	private SurveyQuestionItemService surveyQuestionItemService;
	
	
	public Integer getSurveyId() {
		return surveyId;
	}
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	public List<SurveyQuestionDto> getQuestionDtos() {
		return questionDtos;
	}
	public void setQuestionDtos(List<SurveyQuestionDto> questionDtos) {
		this.questionDtos = questionDtos;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	
	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SurveyQuestionService service) {
		this.service = service;
	}
	public String home(){
		try{
			HttpSession session = getSession();
			SecurityUtil securityUtil = new SecurityUtil(session);
			PermissionVO perVO = new PermissionVO();
			if(securityUtil.havePermission("/base/question!getById")){
				perVO.setHaveGetPermission(true);
			}
			if(securityUtil.havePermission("/base/question!update")){
				perVO.setHaveUpdatePermission(true);
			}
			if(securityUtil.havePermission("/base/question!delete")){
				perVO.setHaveDelPermission(true);
			}
			if(securityUtil.havePermission("/base/question!save")){
				perVO.setHaveSavePermission(true);
			}
			this.getRequest().setAttribute("surveyPermission", perVO);	
			List<SurveyQuestion> questions = ((SurveyQuestionService)service).find("from SurveyQuestion where surveyId="+surveyId);
			questionDtos = new ArrayList<SurveyQuestionDto>();
			if(questions!=null && questions.size()>0){
				for(SurveyQuestion question:questions){
					SurveyQuestionDto dto = new SurveyQuestionDto();
					dto.setQuestion(question);
					List<SurveyAnswer> qAnswers = surveyAnswerService.find("from SurveyAnswer where questionId="+question.getId());
					dto.setAnswers(qAnswers);
					List<SurveyQuestionItem> qItems = surveyQuestionItemService.find("from SurveyQuestionItem where questionId="+question.getId());
					if(qItems!=null && qItems.size()>0){
						dto.setItems(qItems);
					}
					questionDtos.add(dto);
					
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return CONST_QUESTION;
	}
	/**
	 * 新建一个用户
	 */
	synchronized public void save() {
		try{
			Json json = new Json();
			if (data != null) {
				data.setCreatedatetime(new Date(System.currentTimeMillis()));
				data.setUpdatedatetime(new Date(System.currentTimeMillis()));
				data.setSurveyId(surveyId);
				data.setType(type);
				Integer qid = (Integer)service.save(data);
				if(answers!=null){
					for(String answer:answers){
						String[] ans = answer.split(",");
						if(ans!=null && ans.length>0){
							for(String as:ans){
								SurveyAnswer sa = new SurveyAnswer();
								sa.setQuestionId(qid);
								sa.setAnswer(as);
								sa.setCreatedatetime(new Date(System.currentTimeMillis()));
								sa.setUpdatedatetime(new Date(System.currentTimeMillis()));
								surveyAnswerService.save(sa);
							}
						}
					}
				}
				if(items!=null){
					for(String item:items){
						String[] its = item.split(",");
						if(its!=null && its.length>0){
							for(String it:its){
								SurveyQuestionItem si = new SurveyQuestionItem();
								si.setQuestionId(qid);
								si.setTitle(it);
								si.setCreatedatetime(new Date(System.currentTimeMillis()));
								si.setUpdatedatetime(new Date(System.currentTimeMillis()));
								surveyQuestionItemService.save(si);
							}
						}
					}
				}
				json.setMsg("Success!");
				json.setSuccess(true);
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * 更新一个用户
	 */
	synchronized public void update() {
		try{
			Json json = new Json();
			json.setMsg("更新失败！");
			if (data != null && data.getId()!=null && data.getId().toString().trim().length()>0) {
				SurveyQuestion t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t, new String[] { "createdatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
			writeJson(json);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			Integer qid =Integer.parseInt(id);
			SurveyQuestion t = service.getById(qid);
			service.delete(t);
			List<SurveyAnswer> qAnswers = surveyAnswerService.find("from SurveyAnswer where questionId="+qid);
			if(qAnswers!=null && qAnswers.size()>0){
				for(SurveyAnswer answer:qAnswers){
					surveyAnswerService.delete(answer);
				}
			}
			List<SurveyQuestionItem> qItems = surveyQuestionItemService.find("from SurveyQuestionItem where questionId="+qid);
			if(qItems!=null && qItems.size()>0){
				for(SurveyQuestionItem si:qItems){
					surveyQuestionItemService.delete(si);
				}
			}
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}

	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			SurveyQuestion survey = service.getById(Integer.parseInt(id));
			writeJson(survey);
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
