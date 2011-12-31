package org.evgenyry.margarita.config;

public class ConfigException extends RuntimeException {

    private static final long serialVersionUID = -7541421058870570429L;

    public ConfigException(String message) {
	super(message);
    }

    public ConfigException(String message, Throwable cause) {
	super(message, cause);
    }

}
