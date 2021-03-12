package com.eduardozanela.trajectSocialCodeChanllenge.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyRequest;
import com.eduardozanela.trajectSocialCodeChanllenge.dto.BitLyResponse;

@FeignClient(value = "bitLyClient", url = "${client.bitly.url}", configuration = BitLyClientConfig.class)
public interface BitLyClient {
	
	    @RequestMapping(method = RequestMethod.POST, value = "/shorten", produces = "application/json")
	    BitLyResponse shortUrl(@RequestBody BitLyRequest request);
}