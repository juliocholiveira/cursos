package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.dao.TarefaDAO;
import br.com.casadocodigo.loja.model.Tarefa;

@Controller
public class HomeController {

	@Autowired
	TarefaDAO dao;

	@RequestMapping("/")
	public String home() {
		System.out.println("Carregando os produtos");
		dao.adiciona(new Tarefa("teste"));
		return "index";
	}

}
