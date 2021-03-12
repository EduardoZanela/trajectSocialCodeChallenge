package com.eduardozanela.trajectSocialCodeChanllenge.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_PROFILE_HEADINGS")
public class UserProfileHeadings implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_HEADERS_ID_SEQ")
	@SequenceGenerator(name = "USER_HEADERS_ID_SEQ", sequenceName = "USER_HEADERS_ID_SEQ", allocationSize = 1)
	private Long id;
	private String heading;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserProfile user;

	public UserProfileHeadings(String text, UserProfile user) {
		this.heading = text;
		this.user = user;
	}
}
