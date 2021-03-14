package com.eduardozanela.trajectSocialCodeChanllenge.dto;

import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FriendPath {
    private UserProfile user;
    private FriendPath next;

    public FriendPath(UserProfile user) {
        this.user = user;
    }
}
