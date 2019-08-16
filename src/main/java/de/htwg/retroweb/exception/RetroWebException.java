/**
 * Copyright (c) 2018 Sybit GmbH. All rights reserved.
 * 
 * @author Dr. Friedrich-Karl Koschnick, Quality Management, Sybit GmbH
 */

package de.htwg.retroweb.exception;

import java.io.Serializable;

public abstract class RetroWebException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String resourceName;
    private final String fieldName;
    private final Serializable fieldValue;	
	
    protected RetroWebException(String msg, String resourceName, String fieldName, Serializable fieldValue) {
        super(String.format("%s %s %s : '%s'", resourceName, msg, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Serializable getFieldValue() {
        return fieldValue;
    }
    
}
