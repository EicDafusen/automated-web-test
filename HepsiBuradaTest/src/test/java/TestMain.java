
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasketPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;

public class TestMain {

	WebDriver driver;
	WebDriverWait wait;

	String productPagePrice;
	HomePage homePage;
	LoginPage loginPage;
	SearchPage searchPage;
	ProductPage productPage;
	BasketPage basketPage;

	private static Logger LOGGER = null;

	@Before
	public void setup() {
		String projectPath = System.getProperty("user.dir");

		PropertyConfigurator.configure(projectPath + "/src/main/resources/log4j/log4j.properties");

		System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/web-driver/chromedriver.exe");
		LOGGER = LogManager.getLogger(this.getClass().getSimpleName());

		driver = new ChromeDriver();

		wait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();

		this.homePage = new HomePage(driver);
		this.loginPage = new LoginPage(driver);
		this.searchPage = new SearchPage(driver);
		this.productPage = new ProductPage(driver);
		this.basketPage = new BasketPage(driver);
	}

	@Test
	public void homePage() {
		LOGGER.info("Anasayfa Aciliyor Ve Test Ediliyor");
		try {
			homePage.openHomePage();
			Assert.assertEquals("https://www.gittigidiyor.com/", driver.getCurrentUrl());

		} catch (Exception e) {
			LOGGER.error("Anasayfa Acilisinda Hata ! ");
		}

		LOGGER.info("Login Sayfasi Aciliyor Ve Giris Yapiliyor");
		loginPage.logIn("bahadir@gmail.com", "123");

		LOGGER.info("Login Kontrol Ediliyor ");
		//Eðer login sayfasýna gidildiðinde anasayfaya atarsa log olunmuþ demektir
		try {
			loginPage.goToLoginPage();
			Assert.assertEquals("https://www.gittigidiyor.com/", driver.getCurrentUrl());

		} catch (Exception e) {
			LOGGER.error("Login islenminde Hata!");
		}

		LOGGER.info("Aramaya Bilgisayar Yaziliyor");
		searchPage.search("Bilgisayar");

		LOGGER.info("Aramada 2. Sayfaya Gidilip Ve URL Kontrol Ediyor");
		try {
			searchPage.selectPage(2);
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2");

		} catch (Exception e) {
			LOGGER.error("2. Sayfaya Geciste Hata!");
		}

		LOGGER.info("Rastgele Urun Seciliyor Ve Sayfasina Gidiliyor");
		productPage.setURL(searchPage.getRandomItemURL());

		productPage.goToProductPage();

		LOGGER.info("Urun Sepete Ekleniyor");
		productPage.addToBasket();

		LOGGER.info("Urun sayfasýndaki fiyat ile sepetteki fiyat kontrol ediliyor");
		try {
			Thread.sleep(3000);

			String productPagePrice = productPage.checkItemPrice();
			String basketPrice = basketPage.getPrice();
			Assert.assertEquals(productPagePrice, basketPrice);

		} catch (Exception e) {
			LOGGER.error("Sepet ve Urun Sayfasi Fiyatlarinda Hata !");

		}

		LOGGER.info("Urunun Sayisi 2'ye Cikariliyor ve Kontrol Ediliyor ");
		try {
			basketPage.setItemAmount("2");
			String itemAmount = basketPage.getItemAmount();
			Assert.assertEquals(itemAmount, "2");
		} catch (Exception e) {
			LOGGER.info("Urun Sayisi Arttirmada Hata!");
		}

		LOGGER.info("Sepet Bosaltaliyor");
		basketPage.emptyBasket();

		LOGGER.info("TEST TAMAMLANDI !");

	}

}
