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

@RequestMapping("/")
@Controller
public class MainController extends BaseController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("main")
	public String login(HttpServletRequest request) throws IOException{		
		return "/main";
	}
	
}
