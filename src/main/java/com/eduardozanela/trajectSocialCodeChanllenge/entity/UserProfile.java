package com.eduardozanela.trajectSocialCodeChanllenge.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_ID_SEQ")
	@SequenceGenerator(name = "USER_PROFILE_ID_SEQ", sequenceName = "USER_PROFILE_ID_SEQ", allocationSize = 1)
	private Long id;
	private String userName;
	private String url;
	private String shortenedUrl;
	
}
