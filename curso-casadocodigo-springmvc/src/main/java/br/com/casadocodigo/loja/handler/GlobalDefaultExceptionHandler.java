package br.com.casadocodigo.loja.handler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request,
			Exception e) throws Exception {
		// If the exception is annotated with @ResponseStatus rethrow it and let
		// the framework handle it - like the OrderNotFoundException example
		// at the start of this post.
		// AnnotationUtils is a Spring Framework utility class.
		// if (AnnotationUtils.findAnnotation(e.getClass(),
		// ResponseStatus.class) != null)
		// throw e;

		Enumeration<String> lista = request.getHeaderNames();
		while (lista.hasMoreElements()) {
			String element = lista.nextElement();
			System.out.println(element + ": " + request.getHeader(element));
		}

		System.out
				.println("--------------------------------------------------------");

		StackTraceElement[] stackTrace = e.getStackTrace();

		for (StackTraceElement stackTraceElement : stackTrace) {
			System.out.println(stackTraceElement.toString());
		}

		// Otherwise setup and send the user to a default error-view.
		ModelAndView model = new ModelAndView("error");

		model.addObject("codigoErro", 999);
		model.addObject("mensagem", e.getMessage());
		model.addObject("url", request.getRequestURL());

		return model;
	}
}