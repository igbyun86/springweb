package com.springweb.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@RequestMapping(method = RequestMethod.GET, value="/main")
	public String main(@RequestParam Map<String,Object> paramMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/main/main";
	}

}
