package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {

	static String URL = "https://www.gittigidiyor.com/";

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void search(String searchTerm) {
		driver.get(URL);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement searchBarTextField = driver.findElement(By.name("k"));
		searchBarTextField.sendKeys(searchTerm);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		searchBarTextField.submit();
	}

	public void selectPage(int page) {

		// Pencerenin Altýndaki Pop-Up'ý Kapat
		driver.findElement(By.className("policy-alert-close")).click();

		driver.findElement(By.xpath("//a[contains(@href, 'sf=" + page + "')]")).click();

	}

	public String getRandomItemURL() {
		List<WebElement> items = driver.findElements(By.xpath("//a[contains(@class, 'product-link')]"));

		int random = (int) (Math.random() * (items.size()));

		return items.get(random).getAttribute("href");

	}
}
