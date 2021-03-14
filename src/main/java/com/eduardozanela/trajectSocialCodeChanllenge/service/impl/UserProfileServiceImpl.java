package com.eduardozanela.trajectSocialCodeChanllenge.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.FriendPath;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserHeadingsRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardozanela.trajectSocialCodeChanllenge.client.BitLyClient;
import com.eduardozanela.trajectSocialCodeChanllenge.config.RabbitMqConstants;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyRequest;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.exception.UserNotFoundException;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserProfileRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private BitLyClient client;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private UserHeadingsRepository headingsRepository;
	
	@Override
	public UserProfile createUser(UserProfileDTO dto) {
		UserProfile userProfile = modelMapper.map(dto, UserProfile.class);
		userProfile.setShortenedUrl(client.shortUrl(new BitLyRequest(userProfile.getUrl())).getLink());
		userProfile = repository.save(userProfile);
		rabbitTemplate.convertAndSend(RabbitMqConstants.PROCESS_WEB_SCRAPER_EXCHANGE, RabbitMqConstants.PROCESS_WEB_SCRAPER_KEY, dto);	
		return userProfile;
	}

	@Override
	public UserProfile addFriend(String username, String friendUsername) {
		UserProfile friend = repository.findByUsername(friendUsername).orElseThrow(UserNotFoundException::new);
		UserProfile user = repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
		user.getFriends().add(friend);
		friend.getFriends().add(user);
		repository.saveAll(Arrays.asList(friend, user));
		return user;
	}

	@Override
	public UserProfile getUser(String username) {
		return repository.findByUsername(username).get();
	}
	
	@Override
	public List<UserProfile> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public List<FriendPath> findFriends(String username, String heading) throws UserNotFoundException{
		UserProfile user = repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
		List<UserProfileHeadings> headings = headingsRepository.findHeadings(heading.toLowerCase(), user.getId());
		Set<UserProfile> collect = headings.stream().map(UserProfileHeadings::getUser).collect(Collectors.toSet());
		List<FriendPath> response = new ArrayList<>();
		for (UserProfile prof : collect){
			Set<UserProfile> forSearch = new HashSet<>();
			forSearch.add(prof);
			FriendPath friendPath = checkFriendsCircle(user, prof, forSearch);
			if(!Objects.isNull(friendPath.getNext())){
				response.add(friendPath);
			}
		}
		return response;
	}

	private FriendPath checkFriendsCircle(UserProfile user, UserProfile childUser, Set<UserProfile> searchedUsers) {
		FriendPath friendPath = new FriendPath();
		friendPath.setUser(childUser);

		Set<UserProfile> filteredFriends = childUser
				.getFriends()
				.stream()
				.filter(friend -> !searchedUsers.contains(friend))
				.collect(Collectors.toSet());

		for (UserProfile userProfile : filteredFriends) {

			if(user.getFriends().stream().anyMatch(a -> a.getId().equals(userProfile.getId()))){
				friendPath.setNext(new FriendPath(userProfile, new FriendPath(user)));
				return friendPath;
			}
			searchedUsers.add(childUser);
			FriendPath circleFriendPath = checkFriendsCircle(user, userProfile, searchedUsers);
			if(circleFriendPath.getNext() != null) {
				friendPath.setNext(circleFriendPath);
				return friendPath;
			}
		}
		return friendPath;
	}
}
