package com.G_Vichar.Blog.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.Comments;
import com.G_Vichar.Blog.Entity.Posts;
@Repository
public interface CommentsRepo extends JpaRepository<Comments, Long> {

	public Set<Comments> findAllByPostsPostId(Long postsId);

	public Comments findAllByPosts(Posts posts);

}
