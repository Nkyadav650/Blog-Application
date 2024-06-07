package com.G_Vichar.Blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.CategoryEntity;
import com.G_Vichar.Blog.Entity.Posts;
import com.G_Vichar.Blog.Entity.User;

@Repository
public interface PostsRepo extends JpaRepository<Posts, Long> {

	public List<Posts> findByCategory(CategoryEntity cat);

	public List<Posts> findByUser(User user);
	@Query("select p from Posts p where p.title like:key")
	public List<Posts> searchByTitle(@Param("key") String keyword);

}
