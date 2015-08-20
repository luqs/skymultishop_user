package com.sirius.skymall.user.service.base;

import java.util.List;

import com.sirius.skymall.user.model.base.Syresourcetype;
import com.sirius.skymall.user.service.BaseService;

/**
 * 资源类型业务
 * 
 * @author zzpeng
 * 
 */
public interface SyresourcetypeServiceI extends BaseService<Syresourcetype> {

	/**
	 * 获取所有资源类型
	 */
	public List<Syresourcetype> findResourcetype();

}
