package com.example.ohlccryptoapi.setup;

import java.util.*;

import com.example.ohlccryptoapi.domain.model.security.Role;
import com.example.ohlccryptoapi.domain.model.security.User;
import com.example.ohlccryptoapi.persistence.service.security.RoleJpaEntityService;
import com.example.ohlccryptoapi.persistence.service.security.UserJpaEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ApplicationContext ctx;
	  
	private boolean alreadySetup = false;
	

	@Autowired
	private UserJpaEntityService userJpaEntityService;

	@Autowired
	private RoleJpaEntityService roleJpaEntityService;



	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		final Role adminRol = createRolIfNotFound("Rol_ADMIN");

		var roles= new HashSet<Role>();
		roles.add(adminRol);
		createUserIfNotFound("t", "t", roles);
	
		alreadySetup = true;
	}



	@Transactional
	Role createRolIfNotFound(final String name) {
		Role role = roleJpaEntityService.findRoleByName(name);
		if (role == null) {
			role = new Role(null,name,"");
		}
		var roleJpa=roleJpaEntityService.transformToEntity(role);
		roleJpa=roleJpaEntityService.save(roleJpa);
		var domainrole=roleJpaEntityService.transformToDomainModel(roleJpa);
		role = domainrole;
		return role;
	}

	@Transactional
	User createUserIfNotFound(final String username, final String password,
			final Set<Role> Rols) {
		User user = userJpaEntityService.findOne (username);
		if (user == null) {
			user = new User(null,username,password,Rols,true);
			user = userJpaEntityService.saveUser(user);

		}

		//user = userJpaEntityService.transformToDomainModel(userJpaEntityService.save(userJpaEntityService.transformToEntity(user)));
		return user;
	}
	
	
	
	
	
	
	
//	public static void main(String[] args) {
//		SetupDataLoader;
//	}

}