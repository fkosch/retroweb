/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RetroWebException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9153386366344360716L;

	public ResourceAlreadyExistsException( String resourceName, String fieldName, Serializable fieldValue) {
        super("already exists", resourceName, fieldName, fieldValue);
    }
}