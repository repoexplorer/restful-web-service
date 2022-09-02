package com.sid.rest.webservices.restfulwebservice.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {super();}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
