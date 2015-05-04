package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Selenium3 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://trivial3b.herokuapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelenium3() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("id")).clear();
    driver.findElement(By.id("id")).sendKeys("selenium");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("selenium");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Cerrar sesión")).click();
    driver.findElement(By.id("id")).clear();
    driver.findElement(By.id("id")).sendKeys("selenium");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("selenium");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("modo")).click();
    driver.findElement(By.id("modo")).click();
    new Select(driver.findElement(By.id("modo"))).selectByVisibleText("Unirse a partida");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Cerrar sesión")).click();
    driver.findElement(By.id("id")).clear();
    driver.findElement(By.id("id")).sendKeys("jugon");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("jugon");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    new Select(driver.findElement(By.id("modo"))).selectByVisibleText("Unirse a partida");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
    driver.findElement(By.linkText("Cerrar sesión")).click();
    driver.findElement(By.id("id")).clear();
    driver.findElement(By.id("id")).sendKeys("selenium");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("selenium");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    new Select(driver.findElement(By.id("modo"))).selectByVisibleText("Crear Partida");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.id("btn-input")).clear();
    driver.findElement(By.id("btn-input")).sendKeys("Selenium");
    driver.findElement(By.id("btn-chat")).click();
  }

  @After
  public void tearDown() throws Exception {
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
