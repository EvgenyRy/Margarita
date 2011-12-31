package org.evgenyry.margarita.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public class MargaritaConfig {

    private static final MargaritaConfig instance = new MargaritaConfig();

    private static final String ACCOUNT_NAME = "account.name";
    private static final String ACCOUNT_PASSWORD = "account.password";

    public static MargaritaConfig getInstance() {
	return instance;
    }

    private final Map<String, String> configProperties = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

    private MargaritaConfig() {
	Properties defaultProps = new Properties();
	Reader reader = null;
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream("config_default.properties")));
	    defaultProps.load(reader);
	} catch (IOException e) {
	    throw new ConfigException("Faild to load default properties", e);
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
	    } catch (IOException e) {
		// it's ok
	    }
	}

	Properties userProperties = new Properties(defaultProps);
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream("config.properties")));
	    userProperties.load(reader);
	} catch (IOException e) {
	    throw new ConfigException("Faild to load user properties", e);
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
	    } catch (IOException e) {
		// it's ok
	    }
	}
	for (Entry<Object, Object> entr : userProperties.entrySet()) {
	    configProperties.put((String) entr.getKey(), (String) entr.getValue());
	}
    }

    private String getProperty(String propName) {
	String propValue = configProperties.get(propName);
	if (propValue == null) {
	    throw new ConfigException(propName + " not set");
	}
	return propValue;
    }

    public String getAccauntName() {
	return getProperty(ACCOUNT_NAME);
    }

    public String getAccauntPassword() {
	return getProperty(ACCOUNT_PASSWORD);
    }

}
