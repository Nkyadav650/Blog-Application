package com.G_Vichar.Blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.G_Vichar.Blog.Entity.Posts;
import com.G_Vichar.Blog.Entity.Share;

public interface ShareRepository extends JpaRepository<Share, Long> {
    public List<Share> findByPosts(Posts post);
}