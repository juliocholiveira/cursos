package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;

@Transactional
@Controller
public class ProdutoController {

	@Autowired
	ProductDAO productDao;

	@RequestMapping("/produtos/save")
	public String save(Product product) {
		System.out.println("Cadastrando o produto " + product);
		productDao.adiciona(product);
		return "redirect:/produtos/form";
	}

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

}
