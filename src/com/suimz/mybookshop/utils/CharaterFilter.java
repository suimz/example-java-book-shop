package com.suimz.mybookshop.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
/**
 * 设置请求和响应编码
 * @author suimz
 *
 */
@WebFilter(
		filterName="CharaterFilter",
		initParams={@WebInitParam(name="encode",value="utf-8")},
		urlPatterns={"/*"}
		)
public class CharaterFilter implements Filter {
	private String encode;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		encode = arg0.getInitParameter("encode");
	}

}
