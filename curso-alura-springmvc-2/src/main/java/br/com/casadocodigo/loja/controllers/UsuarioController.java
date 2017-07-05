package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.daos.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@Transactional
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@RequestMapping("/gravar")
	public @ResponseBody Usuario gravar(String email, String senha) {

		Role role = new Role();
		role.setNome("ROLE_ADMIN");

		List<Role> roles = new ArrayList<Role>();
		roles.add(role);

		Usuario usuario = new Usuario("julio@email.com", "123456",
				"Júlio César", null);
		usuario.setRoles(roles);

		usuarioDAO.gravar(usuario);

		return usuario;
	}

}
