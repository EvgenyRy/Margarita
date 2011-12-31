package org.evgenyry.margarita.navigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MoswarPageType {

    LOGIN {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    try {
		driver.findElement(By.id("login-email"));
		driver.findElement(By.id("login-password"));
		return true;
	    } catch (NoSuchElementException e) {
		return false;
	    }
	}

	@Override
	public String pagePath() {
	    throw new RuntimeException("You can't navigate to logging page!");
	}
    },

    SEARCH_VICTIM {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith("alley/search/type/")
		    && !driver.getCurrentUrl().endsWith("alley/search/type")
		    && !driver.getCurrentUrl().endsWith("alley/search/again/")
		    && !driver.getCurrentUrl().endsWith("alley/search/again")) {
		return false;
	    }
	    try {
		driver.findElement(By.xpath("//div[@id='content']/div/div[2]/a/div"));
		return true;
	    } catch (NoSuchElementException e) {
		return false;
	    }
	}

	@Override
	public String pagePath() {
	    throw new RuntimeException("You can't navigate to SEARCH_VICTIM page!");
	}
    },

    DUEL_FIGTH {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().contains("alley/fight/")
		    || driver.getCurrentUrl().split("alley/fight/")[1].split("/").length != 2) {
		return false;
	    }
	    // TODO make better validation
	    // try {
	    // driver.findElement(By.id("fight-log"));
	    // return true;
	    // } catch (NoSuchElementException e) {
	    // return false;
	    // }
	    return true;
	}

	@Override
	public String pagePath() {
	    throw new RuntimeException("You can't navigate to SEARCH_VICTIM page!");
	}
    },

    PLAYER {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/player";
	}
    },

    ALLEY {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/alley";
	}
    },

    METRO {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/metro";
	}
    },

    SHOP {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/shop";
	}
    },

    FACTORY {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/factory";
	}
    },

    POLICE {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/police";
	}
    },

    HOME {
	@Override
	public boolean isOnPage(WebDriver driver) {
	    if (!driver.getCurrentUrl().endsWith(this.pagePath() + "/")
		    && !driver.getCurrentUrl().endsWith(this.pagePath())) {
		return false;
	    }
	    // TODO make better validation
	    return true;
	}

	@Override
	public String pagePath() {
	    return "/home";
	}
    }

    ;

    static Logger logger = LoggerFactory.getLogger(MoswarPageType.class);

    public abstract boolean isOnPage(WebDriver driver);

    public abstract String pagePath();

    public static MoswarPageType whatPage(WebDriver driver) {
	for (MoswarPageType page : values()) {
	    if (page.isOnPage(driver)) {
		return page;
	    }
	}
	throw new NavigatorException("Unknow page", driver);
    }

    public static MoswarPageType whatPage(String url) {
	for (MoswarPageType page : values()) {
	    if (url.endsWith(page.pagePath())) {
		return page;
	    }
	}
	throw new NavigatorException("Unknow page for url: " + url, null);
    }

}
