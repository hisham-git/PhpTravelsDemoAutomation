package net.phptravels.utils.browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.phptravels.utils.logging.Log;

public class Browser {
	private static WebDriver driver;

	public Browser(String browser) {
		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "drivers\\ie\\IEDriverServer_win32.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "drivers\\chrome\\chromedriver_win_v2.28.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
			break;
		}
		int timeout = 30;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().maximize();

	}

	/**
	 * Gets the "driver" object.
	 *
	 * @return the "driver" object.
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Close the browser.
	 *
	 */
	public void quit() {
		Log.logInfo("Closing the browser.");
		getDriver().quit();
	}

	/**
	 * Gets URL of the current page.
	 *
	 * @return URL of the current page.
	 */
	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Gets title of the current page.
	 *
	 * @return title of the current page.
	 */
	public static String getPageTitle() {
		return driver.getTitle();
	}
}