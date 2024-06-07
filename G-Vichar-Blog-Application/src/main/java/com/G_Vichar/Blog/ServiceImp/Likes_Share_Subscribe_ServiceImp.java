package com.G_Vichar.Blog.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G_Vichar.Blog.Dto.LikesDto;
import com.G_Vichar.Blog.Dto.ShareDto;
import com.G_Vichar.Blog.Dto.SubscribeDto;
import com.G_Vichar.Blog.Entity.Likes;
import com.G_Vichar.Blog.Entity.Posts;
import com.G_Vichar.Blog.Entity.Share;
import com.G_Vichar.Blog.Entity.Subscribe;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Mapper.Likes_Share_Subscribe_Mapper;
import com.G_Vichar.Blog.Repository.LikesRepository;
import com.G_Vichar.Blog.Repository.PostsRepo;
import com.G_Vichar.Blog.Repository.ShareRepository;
import com.G_Vichar.Blog.Repository.SubscribeRepository;
import com.G_Vichar.Blog.Repository.UserRepo;
import com.G_Vichar.Blog.Service.Likes_Share_Subscribe_Service;

@Service
public class Likes_Share_Subscribe_ServiceImp implements Likes_Share_Subscribe_Service {

	@Autowired
	private PostsRepo postsRepo;
	@Autowired
	private LikesRepository likesRepo;
	@Autowired
	private ShareRepository shareRepo;
	@Autowired
	private SubscribeRepository subscribeRepo;
	@Autowired
	private Likes_Share_Subscribe_Mapper lssMapper;

	@Autowired
	private UserRepo userRepo;

	@Override
	public LikesDto createLikes(LikesDto likesDto, Long postsId,Long userId) {
		Posts posts = postsRepo.findById(postsId)
				.orElseThrow(() -> new ResourceNotFoundException("Post Resource not Available !!"));
		Likes like = lssMapper.likesDtoToLikes(likesDto);
		like.setPosts(posts);
		User user=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Resource not Available !!"));
		like.setUser(user);
		Likes likes = likesRepo.save(like);
		return lssMapper.likesTOLikesDto(likes);
	}

	@Override
	public List<LikesDto> getAllLikes(Long postsId) {
		Posts posts = postsRepo.findById(postsId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));

		List<Likes> likes = likesRepo.findByPosts(posts);
		List<LikesDto> likesDtoList = likes.stream().map(like -> lssMapper.likesTOLikesDto(like))
				.collect(Collectors.toList());
		return likesDtoList;
	}

	@Override
	public void deleteLikes(Long id) {
		Likes likes = likesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		likesRepo.delete(likes);

	}

	@Override
	public ShareDto createShare(ShareDto shareDto, Long postsId) {
		Posts posts = postsRepo.findById(postsId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		Share share = lssMapper.shareDtoToShare(shareDto);
		share.setPosts(posts);
		Share savedShare = shareRepo.save(share);
		return lssMapper.shareToShareDto(savedShare);
	}

	@Override
	public List<ShareDto> getAllShare(Long postsId) {
		Posts posts = postsRepo.findById(postsId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		List<Share> shares = shareRepo.findByPosts(posts);
		List<ShareDto> shareDtoList = shares.stream().map(share -> lssMapper.shareToShareDto(share))
				.collect(Collectors.toList());
		return shareDtoList;
	}

	@Override
	public void deleteShare(Long id) {
		Share share = shareRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		shareRepo.delete(share);
	}

	@Override
	public SubscribeDto createSubscribe(SubscribeDto subscribeDto, Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		Subscribe subscribe = lssMapper.subscribeDtoToSubscribe(subscribeDto);
		subscribe.setUser(user);
		Subscribe subs = subscribeRepo.save(subscribe);
		return lssMapper.subscribeToSubscribeDto(subs);
	}

	@Override
	public List<SubscribeDto> getAllSubscribe(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		List<Subscribe> subscribe = subscribeRepo.findByUser(user);
		List<SubscribeDto> subsList = subscribe.stream().map(sub -> lssMapper.subscribeToSubscribeDto(sub))
				.collect(Collectors.toList());
		return subsList;
	}

	@Override
	public void deleteSubscribe(Long id) {
		Subscribe subs = subscribeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		subscribeRepo.delete(subs);
	}

}
