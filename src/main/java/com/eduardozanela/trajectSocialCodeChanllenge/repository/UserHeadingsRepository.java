package com.eduardozanela.trajectSocialCodeChanllenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;

@Repository
public interface UserHeadingsRepository extends JpaRepository<UserProfileHeadings, Long>{
}
