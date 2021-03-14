package com.eduardozanela.trajectSocialCodeChanllenge.controller;

import java.util.List;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.FriendPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.service.UserProfileService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/users")
public class UserProfileController {

	@Autowired
	
	private UserProfileService service;
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserProfile>> getAllUser() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(service.getAllUsers());
	}
	
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

	@GetMapping(value = "{username}/find/{heading}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FriendPath>> findFriends(@PathVariable("username") String username, @PathVariable("heading") String heading) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(service.findFriends(username, heading));
	}
	
}
