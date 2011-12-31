package org.evgenyry.margarita.config;

public class MargaritaConfig {

    private static final MargaritaConfig instance = new MargaritaConfig();

    public static MargaritaConfig getInstance() {
	return instance;
    }

    public String getAccauntName() {
	return "";
    }

    public String getAccauntPassword() {
	return "";
    }

}
