package com.eduardozanela.trajectSocialCodeChanllenge.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RabbitMqConstants {
	
	public static final String PROCESS_WEB_SCRAPER_EXCHANGE = "webscraper";
	
	public static final String PROCESS_WEB_SCRAPER_DEAD_QUEUE = "web.scraper.dead";
	public static final String PROCESS_WEB_SCRAPER_QUEUE = "web.scraper";
	public static final String PROCESS_WEB_SCRAPER_KEY = "process.web.scrape";

	public static final String XDLE = "x-dead-letter-exchange";
	public static final String XDLRK = "x-dead-letter-routing-key";

}
