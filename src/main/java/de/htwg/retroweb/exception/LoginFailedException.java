/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.exception;

import java.io.Serializable;

public class LoginFailedException extends RetroWebException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9153386366344360716L;

    public LoginFailedException( String resourceName, String fieldName, Serializable fieldValue) {
        super("login failed with", resourceName, fieldName, fieldValue);
    }
}