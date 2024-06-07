package com.G_Vichar.Blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesDto {

    private Long id;
    private PostsDto post;
    private UserDto user;

}
