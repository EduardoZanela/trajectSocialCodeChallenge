package com.eduardozanela.trajectSocialCodeChanllenge;

import com.eduardozanela.trajectSocialCodeChanllenge.client.BitLyClient;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyResponse;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.FriendPath;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserHeadingsRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserProfileRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.service.impl.UserProfileServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileTest {

    @Mock
    private UserProfileRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BitLyClient client;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private UserHeadingsRepository headingsRepository;

    @InjectMocks
    private UserProfileServiceImpl service;

    @Test
    public void createUserTest(){
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", Collections.emptySet(), Collections.emptySet());

        when(modelMapper.map(any(), eq(UserProfile.class))).thenReturn(userProfile);
        when(client.shortUrl(any())).thenReturn(new BitLyResponse("link"));
        when(repository.save(any())).thenReturn(userProfile);

        UserProfile user = service.createUser(new UserProfileDTO("eduardozanela", "Eduardo Zanela", "http://www.google.com.br"));
        Assert.assertEquals(user.getUsername(), "eduardozanela");
    }

    @Test
    public void addFriendTest(){
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());
        UserProfile userProfileRick = new UserProfile(2L, "Rick", "rick", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());

        when(repository.findByUsername(eq("eduardozanela"))).thenReturn(Optional.of(userProfile));
        when(repository.findByUsername(eq("rick"))).thenReturn(Optional.of(userProfileRick));

        UserProfile user = service.addFriend("eduardozanela", "rick");
        Assert.assertNotNull(user.getUsername());
    }

    @Test
    public void getUserTest(){
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());

        when(repository.findByUsername(eq("eduardozanela"))).thenReturn(Optional.of(userProfile));

        UserProfile user = service.getUser("eduardozanela");

        Assert.assertNotNull(user);
    }

    @Test
    public void getAllUsersTest(){
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());

        when(repository.findAll()).thenReturn(Collections.singletonList(userProfile));

        List<UserProfile> users = service.getAllUsers();

        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void findFriendsTest(){
        UserProfile userProfileJohn = new UserProfile(1L, "Eduardo Zanela", "john", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());
        UserProfile userProfileRick = new UserProfile(1L, "Eduardo Zanela", "rick", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());

        Set<UserProfile> rickFriends = new HashSet<>();
        rickFriends.add(userProfileJohn);
        rickFriends.add(userProfile);
        userProfileRick.setFriends(rickFriends);

        Set<UserProfile> johnFriends = new HashSet<>();
        rickFriends.add(userProfileRick);
        userProfileJohn.setFriends(johnFriends);

        Set<UserProfile> profileFriends = new HashSet<>();
        rickFriends.add(userProfileRick);
        userProfile.setFriends(profileFriends);

        UserProfileHeadings heading = new UserProfileHeadings(1L, "heading", userProfileJohn);

        when(repository.findByUsername(eq("eduardozanela"))).thenReturn(Optional.of(userProfile));
        when(headingsRepository.findHeadings(any(), any())).thenReturn(Collections.singletonList(heading));

        List<FriendPath> path = service.findFriends("eduardozanela", "heading");

        Assert.assertNotNull(path);
    }

}
