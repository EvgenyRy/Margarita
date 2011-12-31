package org.evgenyry.margarita.navigator;

import org.evgenyry.margarita.config.MargaritaConfig;
import org.evgenyry.margarita.navigator.page.AlleyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoswarNavigator {

    private static final Logger logger = LoggerFactory.getLogger(MoswarNavigator.class);

    private static final MoswarNavigator instance = new MoswarNavigator();

    public static MoswarNavigator getInstance() {
	return instance;
    }

    private final WebDriver webDriver;
    private final MargaritaConfig config;
    private final String moswarURL = "http://www.moswar.ru";

    private MoswarNavigator() {
	this.webDriver = WebDriverFactory.createDefaultDriver();
	this.config = MargaritaConfig.getInstance();
    }

    private void performLogin() {
	if (MoswarPageType.LOGIN.isOnPage(webDriver)) {
	    webDriver.findElement(By.id("login-email")).clear();
	    webDriver.findElement(By.id("login-email")).sendKeys(config.getAccauntName());
	    webDriver.findElement(By.id("login-password")).clear();
	    webDriver.findElement(By.id("login-password")).sendKeys(config.getAccauntPassword());
	    logger.info("Perform logging to {}", config.getAccauntName());
	    webDriver.findElement(By.cssSelector("button.button")).click();
	    if (MoswarPageType.LOGIN.isOnPage(webDriver)) {
		throw new NavigatorException("Fail to login!", webDriver);
	    }
	}
    }

    public AlleyPage gotoAlleyPage() {
	// goto alley
	gotoPage(MoswarPageType.ALLEY);

	return new AlleyPage(this.webDriver);

    }

    private boolean gotoPage(MoswarPageType page) {
	webDriver.get(moswarURL + page.pagePath());

	MoswarPageType currPage = MoswarPageType.whatPage(webDriver);

	if (MoswarPageType.LOGIN == currPage) {
	    performLogin();
	    webDriver.get(moswarURL + page.pagePath());
	    currPage = MoswarPageType.whatPage(webDriver);
	}

	if (currPage == page) {
	    return true;
	}

	// TODO make some intelligent algorithm, to wait and not to fail!
	// we can be at police / prison / banned ??? game can be closed because
	// of tech works, server can be down! something else?

	// best idea is to use events generation and handling on engine level
	throw new NavigatorException("Wrong page! " + currPage + ", while exprecting: " + page, webDriver);

    }

}
