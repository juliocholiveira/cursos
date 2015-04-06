package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.model.Conta;

@Controller
public class ContaController {
	
	@RequestMapping("/formConta")
	public String formularioConta(){
		return "conta/formulario";
	}

	@RequestMapping("/adicionaConta")
	public String adicionaConta(Conta conta){
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
	 * sendo que devemos receber como parâmetro */
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
	
}
