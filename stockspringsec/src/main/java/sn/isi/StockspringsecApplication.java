package sn.isi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.isi.dao.IRoles;
import sn.isi.dao.IUser;
import sn.isi.entities.Roles;
import sn.isi.entities.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StockspringsecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StockspringsecApplication.class, args);
	}
	@Autowired
	private IUser userdao;
	@Autowired
	private IRoles rolesdao;
	@Override
	public void run(String... args) throws Exception {
		//ROLES
		Roles roles = new Roles();
		roles.setNom("ROLE_USER");
		//rolesdao.save(roles);
		roles = new Roles();
		roles.setNom("ROLE_ADMIN");
		//rolesdao.save(roles);
		//USERS
		User user = new User();
		user.setNom("Dieng");
		user.setPrenom("Aziz");
		user.setEmail("adieng@isi.sn");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordcrip = bCryptPasswordEncoder.encode("passer123");
		user.setPassword(passwordcrip);
		user.setEtat(1);
		List<Roles> rolesList = new ArrayList<>();
		rolesList.add(rolesdao.getById(1));
		user.setRoles(rolesList);
		//userdao.save(user);
		user = new User();
		user.setNom("Seck");
		user.setPrenom("Ngor");
		user.setEmail("nseck@isi.sn");
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		passwordcrip = bCryptPasswordEncoder.encode("passer22");
		user.setPassword(passwordcrip);
		System.out.println(passwordcrip);
		//$2a$10$SxRzxr2OpgWbUT6l.ZSLQumWw4dah4vCQR0DVGAu2XdoC9eHsQM/q
		//$2a$10$SxRzxr2OpgWbUT6l.ZSLQumWw4dah4vCQR0DVGAu2XdoC9eHsQM/q
		user.setEtat(1);
		List<Roles> rolesList2 = new ArrayList<>();
		rolesList2.add(rolesdao.getById(1));
		rolesList2.add(rolesdao.getById(2));
		user.setRoles(rolesList2);
		//userdao.save(user);

		userdao.findAll().forEach(u -> System.out.println(u.getId() + " " + u.getPrenom()));
	}
}
