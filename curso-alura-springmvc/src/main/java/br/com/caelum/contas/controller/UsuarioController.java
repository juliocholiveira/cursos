package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.model.Usuario;

@Controller
public class UsuarioController {
	
	private UsuarioDAO dao;

	@Autowired
	public UsuarioController(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/formLogin")
	public String formLogin(){
		return "usuario/formulario-login";
	}
	
	@RequestMapping("/efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session){
		if (dao.existeUsuario(usuario)){
			// Usu�rio existe, adiciona na sess�o
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		
		// Usu�rio n�o existe, retrona para p�gina de login
		return "redirect:formLogin";
	}
	
	@RequestMapping("/logout")
	public String efetuaLogout(HttpSession session){
		// Invalida a sess�o do usu�rio
		session.invalidate();
		return "redirect:formLogin";
	}
	
	@RequestMapping("/formUsuario")
	public String formUsuario(){
		return "usuario/formulario-usuario";
	}
	
	@RequestMapping("/adicionaUsuario")
	public String adicionaUsuario(@Valid Usuario usuario, BindingResult result){
		if (result.hasErrors()){
			return "usuario/formulario-usuario";
		}
		
		dao.insere(usuario);
		return "redirect:efetuaLogin";
	}

}
