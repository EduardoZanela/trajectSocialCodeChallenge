package com.eduardozanela.trajectSocialCodeChanllenge.listener;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardozanela.trajectSocialCodeChanllenge.config.RabbitMqConstants;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.UserProfileDTO;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfile;
import com.eduardozanela.trajectSocialCodeChanllenge.entity.UserProfileHeadings;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserHeadingsRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.repository.UserProfileRepository;
import com.eduardozanela.trajectSocialCodeChanllenge.service.impl.WebScraperHelper;

@Component
public class WebScraperListener {
	
	@Autowired
	private WebScraperHelper webScraperHelper;
	
	@Autowired
	private UserProfileRepository repository;	
	
	@Autowired
	private UserHeadingsRepository headingsRepository;

	@RabbitListener(queues = RabbitMqConstants.PROCESS_WEB_SCRAPER_QUEUE)
	public void processHeadingsListener(UserProfileDTO message) {
		try {
			Optional<UserProfile> user = repository.findByUsername(message.getUsername());
			if(user.isPresent()) {
				Set<UserProfileHeadings> headings = webScraperHelper.getAllHeadingsFromPage(message.getUrl(), user.get());
				headingsRepository.saveAll(headings);
			}
		} catch (IOException e) {
			
		}
   }

}
