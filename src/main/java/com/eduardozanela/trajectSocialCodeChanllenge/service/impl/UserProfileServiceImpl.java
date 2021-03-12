package com.eduardozanela.trajectSocialCodeChanllenge.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardozanela.trajectSocialCodeChanllenge.client.BitLyClient;
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
	
	@Override
	public UserProfile createUser(UserProfileDTO dto) {
		UserProfile userProfile = modelMapper.map(dto, UserProfile.class);
		userProfile.setShortenedUrl(client.shortUrl(new BitLyRequest(userProfile.getUrl())).getLink());
		userProfile = repository.save(userProfile);
		return userProfile;
	}

	@Override
	public UserProfile addFriend(String username, String friendUsername) {
		Optional<UserProfile> friend = repository.findByUsername(friendUsername);
		Optional<UserProfile> user = repository.findByUsername(username);
		
		if(friend.isEmpty() || user.isEmpty()) {
			throw new UserNotFoundException();
		}
		
		user.get().getFriends().add(friend.get());
		friend.get().getFriends().add(user.get());
		
		repository.saveAll(Arrays.asList(friend.get(), user.get()));
		
		return user.get();
	}

	@Override
	public UserProfile getUser(String username) {
		return repository.findByUsername(username).get();
	}

}
