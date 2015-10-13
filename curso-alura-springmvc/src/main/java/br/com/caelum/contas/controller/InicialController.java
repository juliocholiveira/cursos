package br.com.caelum.contas.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicialController {
	
	@RequestMapping("/")
	public String inicial() {
		return "index";
	}
	
	@RequestMapping("error")
    public String handelError(final HttpServletRequest request, final Model model) {
         /**
      * Process the error details received in the request
     */
		
     // Lets get the status code and uri from the request
     final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
     String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
          if (requestUri == null) {
        requestUri = "Unknown";
      }
      // create a message to be sent back via the model object.
      final String message = MessageFormat.format("{0} returned for {1}",
        statusCode, requestUri); 
 
       model.addAttribute("errorMessage", message);
        return "error2";
    }
	

}
