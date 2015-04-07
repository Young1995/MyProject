package org.svtcc.online.management.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.svtcc.online.management.domain.Resource;
import org.svtcc.online.management.domain.Role;

public class MyInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {
	
	private static final Log logger = LogFactory.getLog(MyInvocationSecurityMetadataSourceService.class);
	
	// 这里自己控制session的open和close，处理lazy加载的问题
	private SessionFactory sessionFactory;

	// 定义一个全局变量，获取所有的配置
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	/**
	 * 这里只有通过构造函数才能注入，通过@Autowired无法注入
	 * @param sessionFactory
	 */
	public MyInvocationSecurityMetadataSourceService(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		loadResourceDefine();
	}

	/**
	 * 获取角色对应的资源
	 */
	@SuppressWarnings("unchecked")
	private void loadResourceDefine() {
		Session session = sessionFactory.openSession();
		// 获取所有的role
		List<Role> roles = session.createQuery("from Role").list();

		// key为uri，value为权限
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (Role role : roles) {
			ConfigAttribute currentRoleConfig = new SecurityConfig(
					role.getRoleName());

			for (Resource resource : role.getRoleResources()) {
				String uri = resource.getResourceURI();
				Collection<ConfigAttribute> configAttributes = null;
				// 如果已经存在，则加入新的role进去
				if (resourceMap.containsKey(uri)) {
					configAttributes = resourceMap.get(uri);
				} else {
					// 如果不存在，则创建一个新的
					configAttributes = new ArrayList<ConfigAttribute>();
				}

				configAttributes.add(currentRoleConfig);
				resourceMap.put(uri, configAttributes);
			}
		}
		session.close();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();

		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("User try to visit %s", url));
		}

		Iterator<String> resources = resourceMap.keySet().iterator();
		while (resources.hasNext()) {
			// 暂时使用正则去匹配，此处后面在修改，
			// e.g. /user/**, /role/**等
			String targetURI = resources.next();
			PathMatcher matcher = new AntPathMatcher();
			if (matcher.match(targetURI, url)) {
				if(logger.isDebugEnabled()) {
					logger.debug(String.format("target url %s is matched.", targetURI));
				}
				return resourceMap.get(targetURI);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (String key : resourceMap.keySet()) {
			allAttributes.addAll(resourceMap.get(key));
		}

		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}
