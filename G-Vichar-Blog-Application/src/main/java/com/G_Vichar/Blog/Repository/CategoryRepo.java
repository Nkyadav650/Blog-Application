package com.G_Vichar.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.CategoryEntity;


@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {

}
