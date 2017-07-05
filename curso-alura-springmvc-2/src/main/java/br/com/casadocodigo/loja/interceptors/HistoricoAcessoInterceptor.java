package br.com.casadocodigo.loja.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.AsyncWebRequestInterceptor;
import org.springframework.web.context.request.WebRequest;

public class HistoricoAcessoInterceptor implements AsyncWebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
		// System.out.println("Passou no preHandle");
		
		System.out.println(request.getSessionId());
		
		Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code",0);
		
		System.out.println(status);
		System.out.println(SecurityContextHolder.getContext()
				.getAuthentication().getName()
				+ " - " + request.getDescription(true));
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		// Usuario usuario = (Usuario)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// System.out.println(usuario.getNome());
		// System.out.println("Passou no postHandle");
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {
		// System.out.println("Contexto: " + request. getContextPath());
		// System.out.println("Referer: " + request.getHeader("Referer"));
		// System.out.println("Drescrição: " + request.getDescription(true));
		// System.out.println("Excessão: " + ex.getMessage() );
		// System.out.println("Usuário: " +
		// request.getUserPrincipal().getName());
		// System.out.println("Passou no afterCompletion");
	}

	@Override
	public void afterConcurrentHandlingStarted(WebRequest request) {
		// System.out.println("Passou no afterConcurrentHandlingStarted");
	}

}
