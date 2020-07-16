package kr.bnbd.mapper;

import kr.bnbd.bean.LoginBean;


public interface LoginMapper {
	
	public int loginProcess(LoginBean loginBean);
	public int Alarmcount();
	public String SelectMinIp();
}
