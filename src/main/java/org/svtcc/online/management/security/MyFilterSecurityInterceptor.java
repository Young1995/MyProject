package org.svtcc.online.management.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {
	private static final Logger LOGGER = Logger.getLogger(MyFilterSecurityInterceptor.class);

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void destroy() {
		LOGGER.info("destory [MyFilterSecurityInterceptor]");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		FilterInvocation filterInvocation = new FilterInvocation(request,
				response, filterChain);
		inovke(filterInvocation);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		LOGGER.info("init [MyFilterSecurityInterceptor]");
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void inovke(FilterInvocation filterInvocation) throws IOException,
			ServletException {
		
		InterceptorStatusToken token = super.beforeInvocation(filterInvocation);

		try {
			filterInvocation.getChain().doFilter(filterInvocation.getRequest(),
					filterInvocation.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}
