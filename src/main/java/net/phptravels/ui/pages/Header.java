package net.phptravels.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Header {

	@FindBy(how = How.CSS, using = "img.logo")
	private WebElement logo;
	
	@FindBy(how = How.CSS, using = "li.pull-right a.dropdown-toggle")
	private WebElement userControlMenu;

	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Account')]")
	private WebElement accountOption;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Logout')]")
	private WebElement logoutOption;
	
	public WebElement getLogo() {
		return logo;
	}

	public WebElement getUserControlMenu() {
		return userControlMenu;
	}

	public WebElement getAccountOption() {
		return accountOption;
	}

	public WebElement getLogoutOption(){
		return logoutOption;
	}
}
