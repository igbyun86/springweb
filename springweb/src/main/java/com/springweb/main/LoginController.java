package com.springweb.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springweb.comm.vo.UserInfo;
import com.springweb.framework.util.SessionUtil;

/**
 * 로그인 Controller
 * @author big
 *
 */
@Controller
public class LoginController {
	protected final Logger log = LogManager.getLogger();

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="/login")
	public String main(@RequestParam Map<String,Object> paramMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		/**
		 * 로그인이 되어있는 경우 main화면으로 이동
		 */
		UserInfo userInfo = SessionUtil.getUserInfo();
		if(userInfo != null) {
			return "redirect:" + request.getContextPath() + "/main";
		}



		return "/main/login";
	}
}
