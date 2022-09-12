package dev.leoduarte.api.leo.app.exceptions;

public class ProfileNotFoundException extends RuntimeException {
	public ProfileNotFoundException(String message) {
		super(message);
	}
}
