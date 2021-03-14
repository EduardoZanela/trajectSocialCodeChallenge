package com.eduardozanela.trajectSocialCodeChanllenge;

import com.eduardozanela.trajectSocialCodeChanllenge.client.BitLyClient;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyResponse;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
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

import java.util.Collections;

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

}
