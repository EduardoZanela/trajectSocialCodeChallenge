package com.eduardozanela.trajectSocialCodeChanllenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.service.UserProfileService;

@RestController
@RequestMapping("/v1/user")
public class UserProfileController {

	@Autowired
	private UserProfileService service;
	
	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> getUser(@PathVariable("username") String username) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(service.getUser(username));
	}
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileDTO dto) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(service.createUser(dto));
	}
	
	@PostMapping(value = "/{username}/{friendusername}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> addfriend(@PathVariable("username") String username, @PathVariable("friendusername") String friendUsername) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(service.addFriend(username, friendUsername));
	}
	
}
