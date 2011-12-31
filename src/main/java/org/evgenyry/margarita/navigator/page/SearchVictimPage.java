package org.evgenyry.margarita.navigator.page;

import org.evgenyry.margarita.navigator.MoswarPage;
import org.evgenyry.margarita.navigator.MoswarPageType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class SearchVictimPage extends MoswarPage {

    protected SearchVictimPage(WebDriver driver) {
	super(driver);
    }

    public int getVictimStats() {
	int health;
	try {
	    health = Integer.parseInt(driver.findElement(
		    By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[1]/div/span")).getText());
	} catch (NoSuchElementException e) {
	    health = Integer.parseInt(driver.findElement(
		    By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li/div/span")).getText());
	}
	int strength = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[2]/div/span")).getText());
	int dexterity = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[3]/div/span")).getText());
	int resistance = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[4]/div/span")).getText());
	int intuition = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[5]/div/span")).getText());
	int attention = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td[3]/div/div[2]/ul/li[6]/div/span")).getText());

	return health + strength + dexterity + resistance + intuition + attention;
    }

    public int getPlayerStats() {
	int health;
	try {
	    health = Integer.parseInt(driver.findElement(
		    By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[1]/div/span")).getText());
	} catch (NoSuchElementException e) {
	    health = Integer.parseInt(driver.findElement(
		    By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li/div/span")).getText());
	}
	int strength = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[2]/div/span")).getText());
	int dexterity = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[3]/div/span")).getText());
	int resistance = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[4]/div/span")).getText());
	int intuition = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[5]/div/span")).getText());
	int attention = Integer.parseInt(driver.findElement(
		By.xpath("//div[@id='content']/table/tbody/tr/td/div/div[2]/ul/li[6]/div/span")).getText());

	return health + strength + dexterity + resistance + intuition + attention;
    }

    public SearchVictimPage nextVictim() {
	driver.findElement(By.xpath("//div[@id='content']/div/div[3]/a/div")).click();

	if (!MoswarPageType.SEARCH_VICTIM.isOnPage(driver)) {
	    return null; // can't move to page
	}

	return new SearchVictimPage(driver);
    }

    public DuelPage atackVictim() {
	driver.findElement(By.xpath("//div[@id='content']/div/div[2]/a/div")).click();

	if (!MoswarPageType.DUEL_FIGTH.isOnPage(driver)) {
	    return null; // can't move to page
	}
	try {
	    driver.findElement(By.id("controls-forward")).click();
	} catch (Exception e) {
	    // do nothing
	}
	return new DuelPage(driver, true);
    }

}
