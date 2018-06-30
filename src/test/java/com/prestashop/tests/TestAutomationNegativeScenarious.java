package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAutomationNegativeScenarious {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("http://automationpractice.com/");
		driver.findElement(By.cssSelector(".login")).click();
	}
	
	public void fill(String locator, String key) {
		driver.findElement(By.cssSelector(locator)).sendKeys(key);
	}
	
	@Test
	public void wrongCredentials() throws InterruptedException {
		fill("#email", "patatesagaci@hotmail.com");
		fill("#passwd", "hahahohohihi");
		//driver.findElement(By.cssSelector("#email")).sendKeys("patatesagaci@hotmail.com");
		//driver.findElement(By.cssSelector("#passwd")).sendKeys("hahahohohihi");
		driver.findElement(By.cssSelector("#SubmitLogin")).sendKeys(Keys.ENTER);
		
		
		boolean isDisplayed= driver.getPageSource().contains("Authentication failed.");
		Thread.sleep(2000);
		Assert.assertTrue(isDisplayed);	
	}
	
	@Test
	public void invalidEmail() throws InterruptedException {
		fill("#email", "patates@@hotmail.com");
		fill("#passwd", "hahahohohihi");
		driver.findElement(By.cssSelector("#SubmitLogin")).sendKeys(Keys.ENTER);
		
		boolean isDisplayed= driver.getPageSource().contains("Invalid email address.");
		Thread.sleep(2000);
		Assert.assertTrue(isDisplayed);
	}
	
	@Test
	public void blankEmail() throws InterruptedException {
		fill("#passwd", "hahahohohihi");
		driver.findElement(By.cssSelector("#SubmitLogin")).sendKeys(Keys.ENTER);
		boolean isDisplayed= driver.getPageSource().contains("An email address required.");
		Thread.sleep(2000);
		Assert.assertTrue(isDisplayed);
	}
	
	@Test
	public void blankPassword() throws InterruptedException {
		fill("#email", "patatesagaci@hotmail.com");
		driver.findElement(By.cssSelector("#SubmitLogin")).sendKeys(Keys.ENTER);
		boolean isDisplayed= driver.getPageSource().contains("Password is required.");
		Thread.sleep(2000);
		Assert.assertTrue(isDisplayed);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
