package steps.Tests;

import java.util.Map;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


import net.phptravels.utils.logging.Log;
import net.phptravels.ui.pages.AdminWelcome;
import net.phptravels.ui.pages.CustomerWelcome;
import net.phptravels.ui.pages.Header;
import net.phptravels.ui.pages.MyAccount;
import net.phptravels.ui.pages.Register;
import net.phptravels.ui.pages.Login;
import net.phptravels.utils.assertions.HardVerify;
import net.phptravels.utils.browsers.Browser;
import net.phptravels.utils.helpers.UiHelper;

/**
 * Created by Abdullah Al Hisham
 */
public class SignUpSteps {
	
	private Register register;
	private MyAccount myAccount;
	private Header header;
	private Login login;

	public SignUpSteps() {
		register = PageFactory.initElements(Browser.getDriver(), Register.class);
		myAccount = PageFactory.initElements(Browser.getDriver(), MyAccount.class);
		header = PageFactory.initElements(Browser.getDriver(), Header.class);
		login = PageFactory.initElements(Browser.getDriver(), Login.class);
	}

	public MyAccount createNewSignIn(Map<String, String> userInfo) {
		Log.logStep(
				"Creating new sign-in for user: [" + userInfo.get("FirstName") + " " + userInfo.get("LastName") + "]");
		myAccount = register.signUp(userInfo);
		return myAccount;
	}

	public void verifyUser(Map<String, String> userInfo) {
		String actual = myAccount.getLoggedInUserName();
		String expected = userInfo.get("FirstName") + " " + userInfo.get("LastName");
		HardVerify.EqualsIgnoreCase(actual, expected, "Verifying logged in user name", "[PASSED]",
				"Expected: [" + expected + "]. Actual: [" + actual + "][FAILED]");
	}

	public MyAccount logIn(Map<String, String> userInfo) {
		login = PageFactory.initElements(Browser.getDriver(), Login.class);
		UiHelper.fluentWaitForElementClickability(login.getLoginBtn(), 10);
		UiHelper.sendKeys(login.getEmailInput(), userInfo.get("Email"));
		UiHelper.sendKeys(login.getPassInput(), userInfo.get("Password"));
		UiHelper.clickAndWait(login.getLoginBtn());
		myAccount = PageFactory.initElements(Browser.getDriver(), MyAccount.class);
		return myAccount;
	}

	public Login logOut() {
		Actions cursor = new Actions(Browser.getDriver());
		UiHelper.click(header.getUserControlMenu());
		cursor.moveToElement(header.getLogoutOption()).click().perform();
		login = PageFactory.initElements(Browser.getDriver(), Login.class);
		UiHelper.fluentWaitForElementClickability(login.getLoginBtn(), 30);
		return login;
	}
	
	public void selectMyProfileTab(){
		myAccount.getMyProfileTab().click();
		UiHelper.fluentWaitForElementClickability(myAccount.getSubmitBtn(), 10);
	}

	public MyAccount updateProfileAndVerifySuccessMsg(Map<String, String> userInfo, String expectedMsg) {
		myAccount.updateProfile(userInfo);
		String actualMsg = myAccount.getProfileUpdateSuccess().getText().trim();
		HardVerify.EqualsIgnoreCase(actualMsg, expectedMsg, "Verifying profile update success message", "[PASSED]",
				"Expected: [" + expectedMsg + "] Actual: [" + actualMsg + "] [FAILED]");
		myAccount = PageFactory.initElements(Browser.getDriver(), MyAccount.class);
		return myAccount;
	}
	
	//////// Implementing generic login/////////////////////
	// https://stackoverflow.com/questions/13371699/how-to-implement-webdriver-pageobject-methods-that-can-return-different-pageobje

	
	public <T> T logIn(Map<String, String> userInfo, Class<T> expectedPage) {
		login = PageFactory.initElements(Browser.getDriver(), Login.class);
		UiHelper.fluentWaitForElementClickability(login.getLoginBtn(), 10);
		UiHelper.sendKeys(login.getEmailInput(), userInfo.get("Email"));
		UiHelper.sendKeys(login.getPassInput(), userInfo.get("Password"));
		UiHelper.clickAndWait(login.getLoginBtn());
		PageFactory.initElements(Browser.getDriver(), expectedPage);
	}
	
	public AdminWelcome loginAsAdmin(String user, String pw){
	    return login(user, pw, AdminWelcome.class);
	}

	public CustomerWelcome loginAsCustomer(String user, String pw){
	    return login(user, pw, CustomerWelcome.class);
	}

	public Login loginWithBadCredentials(String user, String pw){
	    return login(user, pw, Login.class);
	}
	
	public MyAccount createNewSignIn(Map<String, String> userInfo) {
		Log.logStep(
				"Creating new sign-in for user: [" + userInfo.get("FirstName") + " " + userInfo.get("LastName") + "]");
		myAccount = register.signUp(userInfo);
		return myAccount;
	}
}
