package kr.bnbd.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Resource(name = "configuration")
	protected Configuration config;
	
	@Resource(name = "messageSourceAccessor")
	protected MessageSourceAccessor message;
	
	@ModelAttribute
	public void setDefaultModelAttribute(Principal principal, HttpServletRequest request, Model model) {
		model.addAttribute("contextPath", request.getContextPath());
	}

	/**
	 * 예외처리
	 * 
	 * @param e
	 * @return
	 * @see
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		logger.error("ERROR", e.getCause());
		logger.debug(e.getStackTrace());
		// 화면에 StackTrace를 출력하기 위한
		// 처리--------------------------------------------------------------------------------------
		StackTraceElement[] stackTraces = e.getStackTrace();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < stackTraces.length; i++) {
			sb.append(stackTraces[i].toString() + "\n");
		}

		Throwable throwable = e.getCause();

		if (throwable != null) {
			sb.append("##########원인: " + throwable.getClass().getName()
					+ "#####\n");
			sb.append("\n");
			stackTraces = throwable.getStackTrace();
			for (int j = 0; j < stackTraces.length; j++) {
				sb.append(stackTraces[j].toString() + "\n");
			}
		}
		logger.error(sb.toString());
		return "/error/error";
	}
}
