package com.G_Vichar.Blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareDto {

    private Long id;

    private UserDto user;

}
