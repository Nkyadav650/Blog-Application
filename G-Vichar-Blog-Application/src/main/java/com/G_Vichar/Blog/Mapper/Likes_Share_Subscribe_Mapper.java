package com.G_Vichar.Blog.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.G_Vichar.Blog.Dto.LikesDto;
import com.G_Vichar.Blog.Dto.ShareDto;
import com.G_Vichar.Blog.Dto.SubscribeDto;
import com.G_Vichar.Blog.Entity.Likes;
import com.G_Vichar.Blog.Entity.Share;
import com.G_Vichar.Blog.Entity.Subscribe;

@Configuration
public class Likes_Share_Subscribe_Mapper {

	@Autowired
	private ModelMapper modelMapper;

	public LikesDto likesTOLikesDto(Likes likes) {
		return modelMapper.map(likes, LikesDto.class);
	}

	public Likes likesDtoToLikes(LikesDto likesDto) {
		return modelMapper.map(likesDto, Likes.class);
	}

	public ShareDto shareToShareDto(Share share) {
		return modelMapper.map(share, ShareDto.class);
	}

	public Share shareDtoToShare(ShareDto shareDto) {
		return modelMapper.map(shareDto, Share.class);
	}

	public SubscribeDto subscribeToSubscribeDto(Subscribe subscribe) {
		return modelMapper.map(subscribe, SubscribeDto.class);
	}

	public Subscribe subscribeDtoToSubscribe(SubscribeDto subscribeDto) {
		return modelMapper.map(subscribeDto, Subscribe.class);
	}
}
