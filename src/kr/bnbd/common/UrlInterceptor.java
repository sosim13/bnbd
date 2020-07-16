package kr.bnbd.common;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UrlInterceptor extends HandlerInterceptorAdapter {
	/** LOG4J 설정을 위한 Log 객체 */
	Log logger = LogFactory.getLog(getClass());
	
	@Resource(name = "configuration")
	protected Configuration config;
	
	@Resource(name = "messageSourceAccessor")
	protected MessageSourceAccessor message;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hadler){
		String sUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		logger.info(">>sUri:"+sUri);
		
		//로그인이 필요한 페이지
		//private String[] authList = {contextPath+"/alarm/", contextPath+"/gis/", contextPath+"/sweeper/"};
		String[] authList = config.getStringArray("login.auth.authList");
		
		boolean authBool = false;
		HttpSession session = request.getSession();
		//String loginAdminId = (String) session.getAttribute("loginAdminId");
		String loginAdminId = (String) session.getAttribute("user_id");
		
		for(int a=0; a<authList.length; a++){
			logger.debug("UrlInterceptor.preHandle >> contextPath+authList["+a+"] : "+contextPath+authList[a]);
			if(sUri.indexOf(contextPath+authList[a]) > -1){	//로그인 필요 페이지
				authBool = true;
				break;
			}
		}
		
		authList = config.getStringArray("login.auth.exceptList");
		for(int a=0; a<authList.length; a++){
			// uri외 권한 체크 페이지
			if(sUri.equals(authList[a])){	//로그인 필요 페이지
				authBool = true;
				break;
			}
		}
		
		//session 필요 페이지
		if(authBool){
			//로그인 여부확인
			if(loginAdminId == null || "".equals(loginAdminId)){
				session.setAttribute("loginMsg", message.getMessage("message.permission.denied"));
				try {
					session.setAttribute("returnUrl", contextPath+sUri);
					response.sendRedirect(request.getContextPath() + "/login/login.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
