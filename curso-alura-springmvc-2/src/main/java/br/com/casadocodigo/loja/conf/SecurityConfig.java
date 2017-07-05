package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		/*auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER");*/
		
		auth.userDetailsService(usuarioDAO)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**/form").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				//Alterando a página de login padrão
				.and()
			.formLogin().loginPage("/login").permitAll()
				.and()
			.logout()
				/* Por default, o Spring Security só libera que o logout seja feito através de POST, 
				 * justamente para forçar a passagem do token do CSRF. Aqui fizemos um pouco diferente, 
				 * por isso a invocação do método logoutRequestMatcher. A URL passada para ele pode 
				 * ser acessada via GET e, mesmo assim, o processo de logout vai ser iniciado.*/
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				
				/* Note that if you use this mechanism to detect session timeouts, 
				 * it may falsely report an error if the user logs out and then logs 
				 * back in without closing the browser. This is because the session 
				 * cookie is not cleared when you invalidate the session and will be 
				 * resubmitted even if the user has logged out. You may be able to 
				 * explicitly delete the JSESSIONID cookie on logging out, for example 
				 * by using the following syntax in the logout handler:*/
				
				.deleteCookies("JSESSIONID")
				//.and()
				
				/*You can configure Spring Security to detect the submission of an 
				 * invalid session ID and redirect the user to an appropriate URL. 
				 * This is achieved through the session-management element:
				 * */
				//.sessionManagement().invalidSessionUrl("/login?expired")
				.and()
			.requiresChannel()
				//.antMatchers("/login").requiresSecure()
				.antMatchers("/**").requiresSecure();
		
	}
	
}
