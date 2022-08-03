package dev.leoduarte.api.leo.app.persistence.entity;

public enum Role {

	MANAGER,
	ADMIN,
	SUPERVISOR;

	// if the role is null it is configured as user
	public static final Role USER = null;
}
