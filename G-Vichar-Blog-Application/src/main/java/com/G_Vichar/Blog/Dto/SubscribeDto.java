package com.G_Vichar.Blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeDto {

    private Long id;

    private UserDto user;
}
