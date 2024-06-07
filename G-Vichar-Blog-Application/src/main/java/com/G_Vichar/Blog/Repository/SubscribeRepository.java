package com.G_Vichar.Blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.G_Vichar.Blog.Entity.Subscribe;
import com.G_Vichar.Blog.Entity.User;
@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

	public List<Subscribe> findByUser(User user);
}
