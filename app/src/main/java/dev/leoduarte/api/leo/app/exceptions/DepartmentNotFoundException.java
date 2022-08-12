package dev.leoduarte.api.leo.app.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
	public DepartmentNotFoundException(String message) {
		super(message);
	}
}
