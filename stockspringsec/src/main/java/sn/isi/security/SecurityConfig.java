package sn.isi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Spring Boot auto-configuration attempts to automatically configure your Spring
 * application based on the jar dependencies that you have added. For example, if
 * HSQLDB is on your classpath, and you have not manually configured any database
 * connection beans, then Spring Boot auto-configures an in-memory database.
 */

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM user WHERE email =  ?")
			.authoritiesByUsernameQuery("SELECT u.email as principal, r.nom as role FROM user u, roles r, user_roles ur WHERE u.id = ur.users_id and r.id = ur.roles_id and u.email = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();//pour afficher un formulaire de connexion par defaut
		http.formLogin().loginPage("/login");//personnaliser le form de login
		//les droits dun ADMIN et SUPER_ADMIN

		http.authorizeRequests().antMatchers("/accueil")
				.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");

		http.authorizeRequests().antMatchers("/admin/users")
				.hasAnyAuthority("ROLE_ADMIN");

		http.authorizeRequests().antMatchers("/admin/users/save")
				.hasAnyAuthority("ROLE_ADMIN");

		http.authorizeRequests().antMatchers("/admin/roles")
				.hasAnyAuthority("ROLE_ADMIN");

		http.authorizeRequests().antMatchers("/admin/roles/save")
				.hasAnyAuthority("ROLE_ADMIN");
		//gestion des droits
		http.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
	}

}
