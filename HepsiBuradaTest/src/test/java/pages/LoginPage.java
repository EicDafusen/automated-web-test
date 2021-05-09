package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;
	String URL = "https://www.gittigidiyor.com/uye-girisi";

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	public void logIn(String email, String password) {

		driver.get(URL);
		driver.findElement(By.id("L-UserNameField")).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("L-PasswordField")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("gg-login-enter")).click();

		return;
	}

	public void goToLoginPage() {
		driver.get(URL);

	}

}
