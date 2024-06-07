package com.G_Vichar.Blog.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Vichar.Blog.Dto.CategoryDto;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.Service.CategoryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	Map<String, Object> response = new HashMap<>();

	// create
	@PostMapping("/saveCategory")//ok
	public ResponseEntity<Map<String, Object>> saveCategory(@Valid @RequestBody CategoryDto categoryDto)
			throws BadCredentialException {
		CategoryDto saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory != null) {
			response.put("Message", "Data saved seccessfully");
			response.put("Status", HttpStatus.CREATED);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}

	// update
	@PutMapping("/updateCategory/{categoryId}")//ok 
	public ResponseEntity<Map<String, Object>> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Long categoryId) throws BadCredentialException {
		CategoryDto saveCategory = categoryService.updateCategory(categoryDto, categoryId);
		if (saveCategory != null) {
			response.put("Message", "Data Updated seccessfully");
			response.put("Status", HttpStatus.CREATED);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}

	// delete
	@DeleteMapping("/deleteCategory/{categoryId}")//ok
	public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long categoryId)
			throws BadCredentialException {
		categoryService.deleteCategory(categoryId);
		if (categoryService.deleteCategory(categoryId)) {
			response.put("Message", "Data Deleted seccessfully");
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}

	// getById
	@GetMapping("/getCategory/{categoryId}")//ok
	public ResponseEntity<Map<String, Object>> getCategory(@PathVariable Long categoryId)
			throws BadCredentialException {
		CategoryDto category = categoryService.getCategoryById(categoryId);
		if (category != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", category);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}
	}

	// getAll
	@GetMapping("/getAllCategory")//ok
	public ResponseEntity<Map<String, Object>> getAllCategory()throws BadCredentialException {
		List<CategoryDto> category = categoryService.getAllCategory();
		if (category != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", category);
			response.put("Total", category.size());
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}
	}
}
