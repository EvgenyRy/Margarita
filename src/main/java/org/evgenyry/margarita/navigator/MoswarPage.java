package org.evgenyry.margarita.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class MoswarPage {

    protected final WebDriver driver;

    protected MoswarPage(WebDriver driver) {
	this.driver = driver;
    }

    public long getTimeOut() {
	try {
	    String timeoutStr = driver.findElement(By.id("timeout")).getText();
	    if (timeoutStr == null || timeoutStr.isEmpty()) {
		return 0;
	    }
	    return TimeUtil.parseTimeString(timeoutStr);
	} catch (NoSuchElementException e) {
	    return 0;
	}
    }

    public MoswarPageType getTimeOutPageType() {
	return MoswarPageType.whatPage(driver.findElement(By.id("timeout")).getAttribute("href"));
    }

}
