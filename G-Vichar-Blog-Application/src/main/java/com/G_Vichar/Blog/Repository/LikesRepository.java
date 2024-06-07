package com.G_Vichar.Blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.Likes;
import com.G_Vichar.Blog.Entity.Posts;
@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

	public List<Likes> findByPosts(Posts post);
}
