package com.eduardozanela.trajectSocialCodeChanllenge.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_SEQ")
	@SequenceGenerator(name = "USER_PROFILE_SEQ", sequenceName = "USER_PROFILE_SEQ", allocationSize = 1)
	private Long id;
	private String name;
	private String username;
	private String url;
	private String shortenedUrl;
	
	@ManyToMany
	@JoinTable(
	  name = "user_profile_friendship", 
	  joinColumns = @JoinColumn(name = "username"), 
	  inverseJoinColumns = @JoinColumn(name = "friend_username"))
	@JsonIgnoreProperties({"friends"})
	private Set<UserProfile> friends = new HashSet<>();
	
}
