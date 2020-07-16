package kr.bnbd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.bnbd.bean.LoginBean;
import kr.bnbd.service.LoginService;

@RequestMapping("/login/")
@Controller
public class LoginController extends BaseController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("login")
	public String login(HttpServletRequest request) throws IOException{		
		return "/login/login";
	}
	
	@RequestMapping("loginOk")
	public ModelAndView loginOk(HttpServletRequest request, HttpServletResponse response, @ModelAttribute LoginBean loginBean) throws IOException{
		ModelAndView mav = new ModelAndView("/login/loginOk");
		int result = loginService.loginProcess(request, loginBean);
		//int alarm_cnt = loginService.Alarmcount();		로그인시 알람카운트 체크 안함
		String ip = loginService.SelectMinIp();
		
		if(result > 0){	//아이디,패스워드 일치			
			//이전 페이지 확인
			HttpSession session = request.getSession();
			//String returnUrl = (String) session.getAttribute("returnUrl");
			//session.setAttribute("alarm_cnt", alarm_cnt+"");
			session.setAttribute("sip", ip);
			session.setAttribute("user_id", loginBean.getAdminId());
			session.setAttribute("reftime", "60000");		//slotdetail refresh time
			session.setAttribute("reftimeA", "60000");		//alarm refresh time
			
			//if(returnUrl != null && !"".equals(returnUrl)){
				//response.sendRedirect(returnUrl);
			//}else{
				response.sendRedirect(request.getContextPath() + "/main.do");
			//}
		}else{	//아이디,패스워드 불일치
			mav = new ModelAndView("/login/login");
			mav.addObject("loginMsg", message.getMessage("message.login.fail"));
		}
		return mav;
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		loginService.logoutProcess(request);
		response.sendRedirect(request.getContextPath()+"/login/login.do");
	}
}
