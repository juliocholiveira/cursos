package br.com.caelum.contas.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.model.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;
	
	/*
	 * Informa ao spring que deve injetar a dependência, ou seja, passar ao construtor
	 * o parâmetro ContaDAO já instanciado
	 * */
	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/formConta")
	public String formularioConta(){
		return "conta/formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adicionaConta(@Valid Conta conta, BindingResult result){
		
		/*
		 * A anotação @Valid faz com que o Spring valide os valores
		 * passados para a classe Conta e o resultado desta validação
		 * é passado para o parâmetro BindingResult.
		 * 
		 * O result.hasErrors() checa que possui erro na validação
		 * 
		 * */

		if (result.hasErrors()){
			return "conta/formulario";
		}
		
		dao.adiciona(conta);
		
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", dao.lista());
		return mv;
	}
	
	/* Com o Model podemos adicionar campos a jsp, 
	 * sendo que devemos receber como parï¿½metro */
	@RequestMapping("/listaContasComModel")
	public String listaComModel(Model m){
		m.addAttribute("contas", dao.lista());
		return "conta/lista";
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta){
		dao.remove(conta);
		
		/*
		 * Podemos fazer um redirecionamento na lado do servidor (forward) 
		 * ou pelo navegador, no lado do cliente (redirect).
		 * */		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model m){
		m.addAttribute("conta", dao.buscaPorId(id));
		return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta){
		dao.altera(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/alteraContaComRedirect")
	/* Este mÃ©todo redireciona para outra action repassando os atributos */
	public String alteraComRedirect(Conta conta, final RedirectAttributes rd){
		// Repassa o atributo conta
		rd.addFlashAttribute("conta" , conta);
		return "redirect:alteraConta";
	}
	
	@RequestMapping("/pagaConta")
	public void pagaConta(Conta conta, HttpServletResponse response){
		dao.paga(conta.getId());
		response.setStatus(200);
	}
}
