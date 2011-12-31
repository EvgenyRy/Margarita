package org.evgenyry.margarita.navigator.page;

import org.evgenyry.margarita.navigator.MoswarPage;
import org.evgenyry.margarita.navigator.MoswarPageType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AlleyPage extends MoswarPage {

    public AlleyPage(WebDriver driver) {
	super(driver);
    }

    public SearchVictimPage startLooting(LootingType lootType) {
	Select select = new Select(driver.findElement(By.xpath("//form[@id='searchForm']/div/select")));
	select.selectByValue(lootType.getValue());

	driver.findElement(By.xpath("//div[@id='search-enemy-btn']/span/div")).click();

	if (!MoswarPageType.SEARCH_VICTIM.isOnPage(driver)) {
	    return null; // can't move to page
	}

	return new SearchVictimPage(driver);
    }

    public enum LootingType {
	WEAK {
	    @Override
	    public String getValue() {
		return "weak";
	    }
	},

	ENEMY {
	    @Override
	    public String getValue() {
		return "enemy";
	    }
	},

	VICTIM {
	    @Override
	    public String getValue() {
		return "victim";
	    }
	};

	public abstract String getValue();
    }
}
