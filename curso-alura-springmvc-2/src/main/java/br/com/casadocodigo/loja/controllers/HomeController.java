package br.com.casadocodigo.loja.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		return mv; 
	}
	
	// Existe outra forma de configurar um controller. Ver classe AppWebConfiguration
	@RequestMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}

	@RequestMapping("/error")
    public ModelAndView handle(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("status", request.getAttribute("javax.servlet.error.status_code"));
		mv.addObject("message", request.getAttribute("javax.servlet.error.message"));
		mv.addObject("exception", request.getAttribute("javax.servlet.error.exception"));

        return mv;
    }
}
