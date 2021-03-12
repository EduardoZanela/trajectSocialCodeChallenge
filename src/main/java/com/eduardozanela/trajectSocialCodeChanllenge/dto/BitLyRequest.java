package com.eduardozanela.trajectSocialCodeChanllenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BitLyRequest {

	private String domain;
	@JsonProperty("long_url")
	private String longUrl;
	
	public BitLyRequest(String url) {
		this.domain = "bit.ly";
		this.longUrl = url;
	}
	
}