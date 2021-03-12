package com.eduardozanela.trajectSocialCodeChanllenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	public Optional<UserProfile> findByUsername(String username);
}
