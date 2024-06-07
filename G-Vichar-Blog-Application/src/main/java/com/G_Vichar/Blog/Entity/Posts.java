package com.G_Vichar.Blog.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "category", "user", "comment","likes" }) // it is use to handle the stack overflow error
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;

	private String title;

	@Column(length = 10000)
	private String content;

	private String imageName;

	private LocalDateTime timeStamp;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Comments> comment = new HashSet<>();

	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Likes> likes = new HashSet<>();
	
	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Share> share = new HashSet<>();
	
	@OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Subscribe> subscribe = new HashSet<>();
	
	

}
