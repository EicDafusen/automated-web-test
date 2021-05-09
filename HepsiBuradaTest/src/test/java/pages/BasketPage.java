package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BasketPage {

	static String URL = "https://www.gittigidiyor.com/sepetim";
	WebDriver driver;

	public BasketPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getPrice() {
		driver.get(URL);
		return driver.findElement(By.className("total-price")).getText();
	}

	public void setItemAmount(String amount) {
		driver.get(URL);
		Select selectElement = new Select(driver.findElement(By.xpath("//select[@class=\"amount\"] ")));
		selectElement.selectByValue(amount);

	}

	public String getItemAmount() {
		driver.get(URL);
		return driver.findElement(By.xpath("//select[@class=\"amount\"] ")).getAttribute("value");
	}

	public void emptyBasket() {
		driver.get(URL);
		driver.findElement(By.xpath("//a[contains(@title, 'Sil')]")).click();

	}

}
