package com.G_Vichar.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.Roles;
@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

}
