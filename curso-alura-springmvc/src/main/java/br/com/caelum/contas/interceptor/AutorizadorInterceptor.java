package br.com.caelum.contas.interceptor;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AutorizadorInterceptor implements HandlerInterceptor {

	/*
	 * o método afterCompletion é chamado no final da requisição, ou seja após ter renderizado o JSP
	 * */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	/*
	 * Os métodos preHandle e postHandle serão executados antes e depois, respectivamente, da ação
	 * */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object controller) throws Exception {
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("formLogin") || uri.endsWith("formUsuario") || uri.endsWith("adicionaUsuario") || uri.endsWith("efetuaLogin") || uri.endsWith("tabelas") || uri.contains("resources")){
			return true;
		}
		
		if (request.getSession().getAttribute("usuarioLogado") != null){
			return true;
		}
		response.sendRedirect("formLogin");
		return false;
	}

}
