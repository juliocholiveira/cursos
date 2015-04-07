package br.com.caelum.contas.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.model.Conta;

@Controller
public class ContaController {
	
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
		
		ContaDAO dao = new ContaDAO();		
		dao.adiciona(conta);
		
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		ContaDAO dao = new ContaDAO();
		
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", dao.lista());
		
		return mv;
	}
	
	/* Com o Model podemos adicionar campos a jsp, 
	 * sendo que devemos receber como parï¿½metro */
	@RequestMapping("/listaContasComModel")
	public String listaComModel(Model m){
		ContaDAO dao = new ContaDAO();
		
		m.addAttribute("contas", dao.lista());
		
		return "conta/lista";
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta){
		ContaDAO dao = new ContaDAO();
		dao.remove(conta);
		
		/*
		 * Podemos fazer um redirecionamento na lado do servidor (forward) 
		 * ou pelo navegador, no lado do cliente (redirect).
		 * */		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model m){
		ContaDAO dao = new ContaDAO();
		m.addAttribute("conta", dao.buscaPorId(id));
		return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta){
		ContaDAO dao = new ContaDAO();
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
		ContaDAO dao = new ContaDAO();
		dao.paga(conta.getId());
		response.setStatus(200);
	}
}
