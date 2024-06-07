package com.G_Vichar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.G_Vichar.Blog.Entity.Roles;
import com.G_Vichar.Blog.Payload.AppConstants;
import com.G_Vichar.Blog.Repository.RolesRepository;

@SpringBootApplication
public class GVicharBlogApplication implements CommandLineRunner{

	@Autowired
	private RolesRepository rolesRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(GVicharBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	try {
		Roles role=new Roles();
		role.setRoleId(AppConstants.ADMIN_USER);
		role.setRoles("ADMIN_USER");
		
		Roles role1=new Roles();
		role1.setRoleId(AppConstants.NORMAL_USER);
		role1.setRoles("NORMAL_USER");
		List<Roles> list=List.of(role,role1);
		List<Roles> result = this.rolesRepo.saveAll(list);
		result.forEach(r->{System.out.println(r.getRoles());
			}
		);
	}catch (Exception e) {
		e.printStackTrace();
	}
		
	}

}
