package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;
import br.com.casadocodigo.loja.model.ShoppingCart;
import br.com.casadocodigo.loja.model.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShoppingCart shoppingCart;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(Integer productId, @RequestParam BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		System.out.println("Total do carrinho: " + shoppingCart.getTotal());
		return new ModelAndView("redirect:/shopping");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView items() {
		ModelAndView model = new ModelAndView("shoppingCart/items");
		return model;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{productId}")
	public String remove(@PathVariable("productId") Integer productId,
			BookType bookType) {
		shoppingCart.remove(createItem(productId, bookType));
		return "redirect:/shopping";
	}
}
