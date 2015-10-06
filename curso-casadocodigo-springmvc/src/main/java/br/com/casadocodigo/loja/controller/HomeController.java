package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.Product;

@Controller
@Transactional
public class HomeController {

	@Autowired
	ProductDAO dao;

	@RequestMapping("/")
	public String home() {
		System.out.println("Carregando os produtos");
		dao.adiciona(new Product("teste", "descrição", 200));
		return "index";
	}

}
