package com.G_Vichar.Blog.Service;

import java.util.List;

import com.G_Vichar.Blog.Dto.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto saveCategory(CategoryDto category);
	
	//update
	public CategoryDto updateCategory(CategoryDto category,Long categoryId);
	
	//delete
	public boolean deleteCategory(Long categoryId);
	
	//get
	public CategoryDto getCategoryById(Long categoryId);
	
	//getAll
	public List<CategoryDto> getAllCategory();

}
