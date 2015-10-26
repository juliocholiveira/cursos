package br.com.casadocodigo.loja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProductDAO;
import br.com.casadocodigo.loja.model.BookType;
import br.com.casadocodigo.loja.model.Product;

@Transactional
@Controller
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	ProductDAO productDao;

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

	/*
	 * POST: usado quando existe a necessidade de criação de algum recurso; GET:
	 * usado quando o interesse é o de recuperar alguma informação; DELETE: como
	 * o nome diz, deve ser usado para excluir algum recurso; PUT: associado com
	 * alguma operação de atualização de recursos no servidor.
	 */

	/*
	 * Estamos sempre configurando o atributo commandName com o mesmo nome do
	 * tipo do parâmetro recebido no método. Caso você queira alterar isso, por
	 * exemplo passando o valor "objetoAtual", pode utilizar a annotation
	 * @ModelAttribute.
	 * 
	 * 1 save(@Valid @ModelAttribute("objetoAtual") Product product) Essa
	 * annotation pode ser útil caso, por algum motivo, sua empresa tenha uma
	 * padrão para nomes de variáveis disponíveis na sua JSP.
	 */
	@RequestMapping(method = RequestMethod.POST, name = "saveProduct")
	public ModelAndView save(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors() || bindingResult.hasFieldErrors()) {
			return form(product);
		}
		
		productDao.adiciona(product);

		/*
		 * A classe RedirectAttributes é justamente a responsável por isso. Todo
		 * objeto adicionado nela, através do método addFlashAttribute, ficará
		 * disponível até o próximo request.
		 */
		redirectAttributes.addFlashAttribute("sucesso",
				"Produto adicionado com sucesso");

		/*
		 * Essa técnica, onde fazemos um redirect do lado do cliente logo após
		 * um post, é um padrão conhecido da web chamado de Always Redirect
		 * After Post e deve ser sempre utilizado.
		 */
		return new ModelAndView("redirect:/produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("produtos/list");
		modelAndView.addObject("products", productDao.list());
		return modelAndView;
	}
}
