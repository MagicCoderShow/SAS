package com.xuping.sas.intercept;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ManageHandlerInterceptor extends HandlerInterceptorAdapter{
	public String[] allowUrls;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String path = request.getServletPath();
		path = new String(path.getBytes("ISO8859-1"),"UTF-8");
		System.out.println(path);
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");  
		requestUrl = requestUrl.toLowerCase();
		System.out.println(requestUrl);
		if(path.matches(Const.NO_INTERCEPTOR_PATH)|| path.matches(Const.STATIC_PATH)){
			if(path.matches(Const.STATIC_FILE_PATH)){
				ServletContext application = request.getSession().getServletContext();
				String savePath = application.getRealPath("/") + path;
				InputStream fis = new BufferedInputStream(new FileInputStream(savePath));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            response.reset(); 
				response.setContentType("application/octet-stream;charset=ISO8859-1");  
				ServletOutputStream outputStream = response.getOutputStream(); 
				String filename = new String((path.substring(path.lastIndexOf("/")+1, path.length())).getBytes("gb2312"), "iso8859-1");
				response.setHeader("Content-disposition", "attachment; filename=" + filename);
				outputStream.write(buffer);
				outputStream.flush();
				outputStream.close();
				return false;
			}else{
				return true;
			}
		}
		if(null != allowUrls && allowUrls.length>=1) {
			for(String url : allowUrls) {    
				if(requestUrl.contains(url)) {    
					return true;    
				}    
			}
		}
		return false;
	}
	
	public String[] getAllowUrls() {
		return allowUrls;
	}
	
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}
}
