/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import de.htwg.retroweb.service.bcrypt.BCrypt;

@Service
public class EncryptionService implements Encryptable {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EncryptionService.class);
	
	@Override
	public String encryptPassword(String originalPassword) {
		LOG.debug("--> encryptPassword");
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
		LOG.debug("<-- encryptPassword");
		return generatedSecuredPasswordHash;
	}
	
	@Override
	public boolean passwordMatches(String originalPassword, String generatedSecuredPasswordHash) {
		LOG.debug("passwordMatches");
		return BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
	}
	
	
}
