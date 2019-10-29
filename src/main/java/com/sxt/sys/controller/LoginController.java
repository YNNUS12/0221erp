package com.sxt.sys.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxt.sys.utils.ActiverUser;

@Controller
@RequestMapping("login")
public class LoginController {

	/**
	 * 登陆
	 */
	@RequestMapping("login")
	public String login(String username, String password, Model model,HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 4.创建主体 只要线程不变,Subject不变
		Subject subject = SecurityUtils.getSubject();
		try {
			// 5登陆
			subject.login(token);
			ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
			session.setAttribute("user", activerUser.getUser());
			return "system/index";
		} catch (UnknownAccountException e) {
			model.addAttribute("error", "用户名不存在");
			return "system/login";
		} catch (IncorrectCredentialsException e) {
			model.addAttribute("error", "密码不正确");
			return "system/login";
		}

	}

}
