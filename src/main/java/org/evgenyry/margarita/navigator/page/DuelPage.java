package org.evgenyry.margarita.navigator.page;

import org.evgenyry.margarita.navigator.MoswarPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DuelPage extends MoswarPage {

    private final boolean isIamAtacker;

    public DuelPage(WebDriver driver, boolean isIamAtacker) {
	super(driver);
	this.isIamAtacker = isIamAtacker;
    }

    public String getAtackerName() {
	return driver.findElement(By.cssSelector("div.fighter1 > span.user > a")).getText();
    }

    public String getVictimName() {
	return driver.findElement(By.cssSelector("div.fighter2 > span.user > a")).getText();
    }

    public String getWinnerName() {
	String winnerStr = driver.findElement(By.xpath("//div[@id='content']/table[2]/tbody/tr/td[2]/ul/li/div/b/b"))
		.getText();
	// cutoff level value
	return winnerStr.substring(0, winnerStr.indexOf(" ["));
    }

    public boolean isIamWinner() {
	try {
	    driver.findElement(By.id("controls-forward")).click();
	} catch (Exception e) {
	    // do nothing
	}
	return isIamAtacker && getAtackerName().equals(getWinnerName()) || !isIamAtacker
		&& getVictimName().equals(getWinnerName());

    }
}
