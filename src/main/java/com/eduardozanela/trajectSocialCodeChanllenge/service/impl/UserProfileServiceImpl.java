package com.eduardozanela.trajectSocialCodeChanllenge.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserProfileRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository repository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserProfile createUser(UserProfileDTO dto) {
		UserProfile userProfile = modelMapper.map(dto, UserProfile.class);
		userProfile = repository.save(userProfile);
		return userProfile;
	}

}
