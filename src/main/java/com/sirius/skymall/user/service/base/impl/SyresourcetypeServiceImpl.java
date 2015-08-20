package com.sirius.skymall.user.service.base.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sirius.skymall.user.model.base.Syresourcetype;
import com.sirius.skymall.user.service.base.SyresourcetypeServiceI;
import com.sirius.skymall.user.service.impl.BaseServiceImpl;

/**
 * 资源类型业务逻辑
 * 
 * @author zzpeng
 * 
 */
@Service
public class SyresourcetypeServiceImpl extends BaseServiceImpl<Syresourcetype> implements SyresourcetypeServiceI {

	/**
	 * 为列表添加了缓存，查询一次过后，只要不重启服务，缓存一直存在，不需要再查询数据库了，节省了一些资源
	 * 
	 * 在ehcache.xml里面需要有对应的value
	 * 
	 * <cache name="SyresourcetypeServiceCache"
	 * 
	 * key是自己设定的一个ID，用来标识缓存
	 */
	@Override
	@Cacheable(value = "SyresourcetypeServiceCache", key = "'SyresourcetypeList'")
	public List<Syresourcetype> findResourcetype() {
		return find();
	}

}
