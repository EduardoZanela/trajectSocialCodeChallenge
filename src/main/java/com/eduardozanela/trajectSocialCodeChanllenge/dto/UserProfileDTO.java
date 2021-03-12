package com.eduardozanela.trajectSocialCodeChanllenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDTO {
	
	private String username;
	private String name;
	private String url;
}
