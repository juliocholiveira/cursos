package br.com.caelum.contas.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Usuario {
	private String login;
	private String senha;

	@NotNull(message="{usuario.formulario.login.obrigatoria}")
	@Size(min=5, message="{usuario.formulario.login.tamanho}")
	@NotBlank(message="{usuario.formulario.login.obrigatoria}")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
