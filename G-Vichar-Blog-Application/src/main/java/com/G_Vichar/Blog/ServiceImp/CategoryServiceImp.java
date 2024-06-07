package com.G_Vichar.Blog.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G_Vichar.Blog.Dto.CategoryDto;
import com.G_Vichar.Blog.Entity.CategoryEntity;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Mapper.CategoryMapper;
import com.G_Vichar.Blog.Repository.CategoryRepo;
import com.G_Vichar.Blog.Service.CategoryService;
@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CategoryMapper categoryMappar;

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		CategoryEntity category = categoryMappar.categoryDtoToCategoryEntity(categoryDto);
		CategoryEntity savedCategory = categoryRepo.save(category);
		return categoryMappar.categoryEntityToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		CategoryEntity category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found !!"));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		CategoryEntity updatedCategory = categoryRepo.save(category);
		return categoryMappar.categoryEntityToCategoryDto(updatedCategory);
	}

	@Override
	public boolean deleteCategory(Long categoryId) {
		CategoryEntity category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found !!"));
		this.categoryRepo.delete(category);
		if (category != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		CategoryEntity category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Id Not Found !!"));
		return this.categoryMappar.categoryEntityToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<CategoryEntity> categoryList = categoryRepo.findAll();
		List<CategoryDto> categoryDto = categoryList.stream()
				.map((cat) -> this.categoryMappar.categoryEntityToCategoryDto(cat)).collect(Collectors.toList());
		return categoryDto;
	}

}
