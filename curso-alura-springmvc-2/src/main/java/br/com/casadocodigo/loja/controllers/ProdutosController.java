package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping(name = "produtoForm", value = "/form")
	public ModelAndView form(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		if (produto.getId() != null) {
			produto = produtoDAO.buscaPorId(produto.getId());
			mv.addObject("produto", produto);
		}
		return mv;
	}

	@RequestMapping(name = "produtoGravar", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Produto produto,
			BindingResult bindingResult, RedirectAttributes redirect) {
		if (bindingResult.hasErrors()) {
			return form(produto);
		}
		ModelAndView mv = new ModelAndView("redirect:produtos");
		produtoDAO.gravar(produto);
		mv.addObject("sucesso", "Produto cadastrado com sucesso!");

		/*
		 * Para redirecionar atributos entre requisições podemos utilizar o
		 * RedirectAttributes e o método redirect.addFlashAttribute("sucesso",
		 * "Produto cadastrado com sucesso!");
		 * 
		 * Caso queiramos fazer uso deste atributo na outra requisição, devemos
		 * utilizar a anotação
		 * 
		 * @ModelAttribute(nomeDoAtributo") e utilizar o método mv.addObject("
		 * sucesso", "Produto cadastrado com sucesso!"); da classe ModelAndView.
		 */
		return mv;
	}

	@RequestMapping(name = "produtoListar", method = RequestMethod.GET)
	@Cacheable(value="ProdutosController-listar")
	public ModelAndView listar(@ModelAttribute("sucesso") String sucesso) {
		ModelAndView mv = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDAO.listar();
		mv.addObject("produtos", produtos);
		mv.addObject("sucesso", sucesso);
		return mv;
	}

	@RequestMapping(name = "produtoDetalhar", value = "/detalhe/{id}")
	public ModelAndView detalhar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("produtos/detalhe");
		mv.addObject("produto", produtoDAO.buscaPorId(id));
		return mv;
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Produto detalheJson(@PathVariable("id") Integer id){
		return produtoDAO.buscaPorId(id);
	}
	
	@RequestMapping("/htmlJson")
	@ResponseBody
	public ModelAndView htmlJson(){
		return new ModelAndView("produtos/ok");
	}

}
