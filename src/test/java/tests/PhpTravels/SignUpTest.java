
package tests.PhpTravels;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.phptravels.utils.logging.Log;
import net.phptravels.utils.dataProvider.ExcelDataMapper;
import net.phptravels.utils.helpers.Nav;
import steps.Tests.SignUpSteps;

@Test(groups = { "SignUp" })
public class SignUpTest extends AbstractTest {

	private SignUpSteps steps;
	private static final String profileSuccessMsg = "Profile Updated Successfully.";

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		Log.logTestClassStart(this.getClass());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		Log.logTestClassEnd(this.getClass());
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		Log.logTestMethodStart(method);
		steps = new SignUpSteps();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method, ITestResult result) {
		steps.logOut();
		Log.logTestMethodEnd(method, result);
	}

	@Test(groups = {
			"SignUpUser" }, description = "Check user creation", dataProvider = "getExcelData", dataProviderClass = ExcelDataMapper.class)
	public void signUpUserTest(Map<String, String> testData) {
		Nav.toURL(baseUrl + "/register");
		steps.createNewSignIn(testData);
		steps.verifyUser(testData);
	}

	@Test(groups = {
			"UpdateProfile" }, dependsOnGroups = "SignUpUser", description = "Check user profile update", dataProvider = "getExcelData", dataProviderClass = ExcelDataMapper.class)
	public void updateProfileTest(Map<String, String> testData) {
		Nav.toURL(baseUrl + "/login");
		steps.logIn(testData);
		steps.selectMyProfileTab();
		steps.updateProfileAndVerifySuccessMsg(testData, profileSuccessMsg);
	}
}
