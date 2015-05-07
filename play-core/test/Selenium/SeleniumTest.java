package selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import server.StartTestServer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		StartTestServer.initServer();

		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSelenium1() throws Exception {
		driver.get("http://localhost:" + StartTestServer.PORT + "/");
		driver.findElement(By.cssSelector("form a")).click();
		driver.findElement(By.id("id")).clear();
		driver.findElement(By.id("id")).sendKeys("selenium");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("selenium");
		driver.findElement(By.id("password2")).clear();
		driver.findElement(By.id("password2")).sendKeys("selenium");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}

	@Test
	public void testSelenium2() throws Exception {
		driver.get("http://localhost:" + StartTestServer.PORT + "/");
		driver.findElement(By.id("id")).clear();
		driver.findElement(By.id("id")).sendKeys("selenium");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("selenium");
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.findElement(By.id("logout")).click();
		driver.findElement(By.id("id")).clear();
		driver.findElement(By.id("id")).sendKeys("selenium");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("selenium");
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.findElement(By.id("modo")).click();
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.findElement(By.id("logout")).click();
		driver.findElement(By.id("id")).clear();
		driver.findElement(By.id("id")).sendKeys("jugon");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("jugon");
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.findElement(By.id("id")).clear();
		driver.findElement(By.id("id")).sendKeys("selenium");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("selenium");
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.findElement(By.cssSelector("button.btn-primary")).click();
	}

	@After
	public void tearDown() throws Exception {
		if (driver != null)
			driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
