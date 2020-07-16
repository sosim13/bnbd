package kr.bnbd.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.bnbd.bean.LoginBean;
import kr.bnbd.mapper.LoginMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource(name = "messageSourceAccessor")
	protected MessageSourceAccessor message;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public int loginProcess(HttpServletRequest request, LoginBean loginBean) {
		int result = loginMapper.loginProcess(loginBean);
		if(result > 0){
			HttpSession session = request.getSession();
			session.setAttribute("loginAdminId", loginBean.getAdminId());
		}
		return result;
	}
	
	@Override
	public void logoutProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//session.removeAttribute("loginAdminId");
		session.removeAttribute("user_id");
		session.removeAttribute("sip");
		session.setAttribute("logoutMsg", message.getMessage("message.logout.success"));
	}
	
	@Override
	public int Alarmcount() {
		int result = loginMapper.Alarmcount();
		return result;
	}
	
	@Override
	public String SelectMinIp() {
		String result = loginMapper.SelectMinIp();
		return result;
	}
	
}
