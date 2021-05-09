package pages;

import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;

	final public static String URL = "https://www.gittigidiyor.com/";

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	public void openHomePage() {
		driver.get(URL);

		return;

	}
}
