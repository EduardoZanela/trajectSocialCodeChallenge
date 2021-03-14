package com.eduardozanela.trajectSocialCodeChanllenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;

import javax.ws.rs.PathParam;
import java.util.List;

@Repository
public interface UserHeadingsRepository extends JpaRepository<UserProfileHeadings, Long>{

    /**/

    @Query(nativeQuery = true, value = "select * from user_profile_headings uh where " +
            "uh.user_id != :userId and " +
            "uh.user_id not in (select friend_username from user_profile_friendship where username = :userId) and " +
            "LOWER(uh.heading) like %:heading%")
    public List<UserProfileHeadings> findHeadings(@Param("heading") String heading, @Param("userId") Long userId);
}
