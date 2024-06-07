package com.G_Vichar.Blog.Payload;

import java.util.List;

import com.G_Vichar.Blog.Dto.PostsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {

	private List<PostsDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean lastPage;
	
}