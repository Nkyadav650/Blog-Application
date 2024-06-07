package com.G_Vichar.Blog.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = { "user"})// this is use to handle stackoverflow error
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String content;
	@ManyToOne
	private Posts posts;
	@ManyToOne()
	private User user;
}
