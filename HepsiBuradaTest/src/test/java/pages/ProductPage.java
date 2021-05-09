package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

	WebDriver driver;
	String URL;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public void goToProductPage() {
		driver.get(URL);
	}

	public void addToBasket() {

		driver.findElement(By.id("add-to-basket")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	public String checkItemPrice() {

		String productPrice = driver.findElement(By.id("sp-price-lowPrice")).getText();

		// Ýndirim yoksa gerçek fiyatý al
		if (productPrice.equals("")) {

			productPrice = driver.findElement(By.id("sp-price-highPrice")).getText();

		}

		return productPrice;

	}
}
