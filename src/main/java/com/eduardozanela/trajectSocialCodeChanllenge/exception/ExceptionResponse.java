package com.eduardozanela.trajectSocialCodeChanllenge.exception;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

	private String message;
	private String error;

    public ExceptionResponse(String message, String error) {
        this.message = message;
        this.error = error;
    }
}

