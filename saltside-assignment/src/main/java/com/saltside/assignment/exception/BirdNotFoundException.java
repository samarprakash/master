package com.saltside.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "bird does not exist")
public class BirdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8790211652911971729L;

	public BirdNotFoundException(String id) {
		super("Bird doesn't exist with id=[{}]"+id);
	}
}