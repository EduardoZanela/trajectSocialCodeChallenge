package com.eduardozanela.trajectSocialCodeChanllenge;

import com.eduardozanela.trajectSocialCodeChanllenge.client.BitLyClient;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyResponse;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.FriendPath;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;
import com.eduardozanela.trajectSocialCodeChanllenge.listener.WebScraperListener;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserHeadingsRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserProfileRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.service.impl.UserProfileServiceImpl;
import com.eduardozanela.trajectSocialCodeChanllenge.service.impl.WebScraperHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebScraperListnerTest {

    @Mock
    private WebScraperHelper webScraperHelper;

    @Mock
    private UserProfileRepository repository;

    @Mock
    private UserHeadingsRepository headingsRepository;

    @InjectMocks
    private WebScraperListener test;

    @Test
    public void processHeadingsListenerTest() throws IOException {
        UserProfile userProfile = new UserProfile(1L, "Eduardo Zanela", "eduardozanela", "URL", "SHORT URL", new HashSet<>(), Collections.emptySet());

        when(repository.findByUsername(eq("eduardozanela"))).thenReturn(Optional.of(userProfile));
        when(webScraperHelper.getAllHeadingsFromPage(any(), any())).thenReturn(Collections.singleton(new UserProfileHeadings()));

        test.processHeadingsListener(new UserProfileDTO("eduardozanela", "Eduardo Zanela", "URL"));

        verify(headingsRepository, times(1)).saveAll(any());
    }

}
