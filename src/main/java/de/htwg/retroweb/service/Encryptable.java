package de.htwg.retroweb.service;

public interface Encryptable {

	public String encryptPassword(String originalPassword);
	public boolean passwordMatches(String originalPassword, String generatedSecuredPasswordHash);
}
