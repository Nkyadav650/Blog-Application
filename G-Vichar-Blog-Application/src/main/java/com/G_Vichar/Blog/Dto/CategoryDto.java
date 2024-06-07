package com.G_Vichar.Blog.Dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	private Long categoryId;
	@NotEmpty
	@Size(min=4)
	private String categoryTitle;
	@NotEmpty
	@Size(min=10)
	private String categoryDescription;
}
