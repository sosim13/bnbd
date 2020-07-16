package kr.bnbd.service;

import javax.servlet.http.HttpServletRequest;

import kr.bnbd.bean.LoginBean;


public interface LoginService {
	
	public int loginProcess(HttpServletRequest request, LoginBean loginBean);
	
	public void logoutProcess(HttpServletRequest request);
	
	public int Alarmcount();
	
	public String SelectMinIp();
}
