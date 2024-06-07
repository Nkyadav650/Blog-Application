package com.G_Vichar.Blog.Service;

import java.util.List;

import com.G_Vichar.Blog.Dto.LikesDto;
import com.G_Vichar.Blog.Dto.ShareDto;
import com.G_Vichar.Blog.Dto.SubscribeDto;

public interface Likes_Share_Subscribe_Service {

	// create likes
	public LikesDto createLikes(LikesDto likesDto, Long postsId,Long userId);

	// getAll Likes
	public List<LikesDto> getAllLikes(Long postsId);

	// Delete Likes
	public void deleteLikes(Long id);

	// create Share
	public ShareDto createShare(ShareDto shareDto, Long postsId);

	// getAll Share
	public List<ShareDto> getAllShare(Long postsId);

	// Delete Share
	public void deleteShare(Long id);

	// create Subscribe
	public SubscribeDto createSubscribe(SubscribeDto subscribeDto, Long userId);

	// getAll Likes
	public List<SubscribeDto> getAllSubscribe(Long userId);

	// Delete Subscribe
	public void deleteSubscribe(Long id);
}
