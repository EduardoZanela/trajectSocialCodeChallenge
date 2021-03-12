package com.eduardozanela.trajectSocialCodeChanllenge.service;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;

public interface UserProfileService {
	
	public UserProfile createUser(UserProfileDTO dto);

	public UserProfile addFriend(String username, String friendUsername);

	public UserProfile getUser(String username);
}
