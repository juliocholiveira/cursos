package br.com.casadocodigo.loja.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/error")
	public String handelError(final HttpServletRequest request,
			final Model model) {

		/*
		 * Neste site encontra-se todas as constantes de erros
		 * https://tomcat.apache
		 * .org/tomcat-7.0-doc/servletapi/constant-values.html
		 * 
		 * Neste aqui encontra-se todos os c�digos de erros HTTP
		 * http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
		 */

		Enumeration<String> lista = request.getHeaderNames();
		while (lista.hasMoreElements()) {
			String element = lista.nextElement();
			System.out.println(element + ": " + request.getHeader(element));
		}

		// Lets get the status code and uri from the request
		final Integer codigoErro = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		String mensagem = null;

		switch (codigoErro) {
		case 404:
			mensagem = "Não encontrato";
			break;
		case 500:
			mensagem = "Erro interno do servidor";
			break;

		default:
			mensagem = "Erro inesperado";
			break;
		}

		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");

		if (requestUri == null) {
			requestUri = "Desconhecido";
		}

		model.addAttribute("codigoErro", codigoErro);
		model.addAttribute("mensagem", mensagem);
		model.addAttribute("url", requestUri);
		return "error";
	}

}
