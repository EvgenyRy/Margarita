package org.evgenyry.margarita.navigator;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MargaritaFirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    private static final String FF_PROFILE_PATH = "ffox-profile/";

    private WebDriverFactory() {
	// private constructor
    }

    public static WebDriver createDefaultDriver() {
	return createFirefoxDriver();
    }

    private static FirefoxDriver createFirefoxDriver() {
	File profileDir = new File(FF_PROFILE_PATH);
	FirefoxProfile ffProfile = new MargaritaFirefoxProfile(profileDir);
	logger.info("Use FireFox profile from: " + profileDir.getAbsolutePath());

	return new FirefoxDriver(ffProfile);
    }
}
