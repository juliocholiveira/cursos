package br.com.casadocodigo.loja.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.model.PaymentData;
import br.com.casadocodigo.loja.model.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();

		String uriToPay = "http://book-payment.herokuapp.com/payment";
		try {
			String response = restTemplate.postForObject(uriToPay,
					new PaymentData(total), String.class);
			return "redirect:/payment/success";
		} catch (HttpClientErrorException exception) {
			return "redirect:/payment/error";
		}

	}

	@RequestMapping(value="success")
	public String success(){
		System.out.println("success...");
		return "redirect:/produtos";
	}

	@RequestMapping(value="error")
	public String error(){
		System.out.println("error...");
		return "redirect:/shopping";
	}

}