package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAutomationPositiveScenarious {

	WebDriver driver;
	Random random = new Random();
	Faker faker = new Faker();
	Select select;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() {

	driver.get("http://automationpractice.com");
	driver.findElement(By.cssSelector(".login")).click();

	String email = faker.internet().emailAddress();
	driver.findElement(By.id("email_create")).sendKeys(email);
	driver.findElement(By.id("SubmitCreate")).click();
	        
	int numGender = faker.number().numberBetween(1, 3);
	System.out.println(numGender);
	if(numGender == 1) {
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
	}else {
		driver.findElement(By.xpath("//input[@id='id_gender2']")).click();
	}
	        
	String firstName = faker.name().firstName();
	driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
	
	String lastName = faker.name().lastName();
	driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
	        
	String password = faker.internet().password();
	driver.findElement(By.name("passwd")).sendKeys(password);
	        
	//Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));

	//countryElem.selectByIndex(data.number().numberBetween(1, countryElem.getOptions().size()));
	    
	Select dateOfBirthDay = new Select(driver.findElement(By.id("days")));
	dateOfBirthDay.selectByIndex(random.nextInt(30));
	
	Select dateOfBirthMonth = new Select(driver.findElement(By.id("months")));
	dateOfBirthMonth.selectByIndex(random.nextInt(11));
	
	Select dateOfBirthYear = new Select(driver.findElement(By.id("years")));
	//dateOfBirthYear.selectByIndex(faker.number().numberBetween(1900, 2018));
	dateOfBirthYear.selectByIndex(random.nextInt(119));
	//dateOfBirthYear.selectByVisibleText(Integer.toString(random.nextInt(119) + 1900));  //BUNU SOR

	driver.findElement(By.id("uniform-newsletter")).click();
	driver.findElement(By.id("optin")).click();
	
	driver.findElement(By.id("company")).sendKeys(faker.company().name());
	
	String address = faker.address().streetAddress();
	driver.findElement(By.id("address1")).sendKeys(address);
	
	
	driver.findElement(By.id("city")).sendKeys(faker.address().city());
	
	String state = faker.address().state();
	driver.findElement(By.id("id_state")).sendKeys(state);

	String zip = "" + faker.number().numberBetween(10000, 100000);
	System.out.println(zip);
	driver.findElement(By.id("postcode")).sendKeys(zip);
	
	driver.findElement(By.id("id_country")).sendKeys(faker.address().country());
	
	driver.findElement(By.id("phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());
	
	driver.findElement(By.id("alias")).sendKeys(address);
	
	driver.findElement(By.id("submitAccount")).click();
	
	//sign out once the registration is complete
	driver.findElement(By.cssSelector("a[href='http://automationpractice.com/index.php?mylogout=']")).click();
	
	//log back using the same information
	driver.findElement(By.cssSelector(".login")).click();
	
	driver.findElement(By.cssSelector("#email")).sendKeys(email);
	driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
	driver.findElement(By.cssSelector("#SubmitLogin")).sendKeys(Keys.ENTER);

	//verify the correct user name and last name is displayed on the top right
	
	String actual = driver.findElement(By.cssSelector("a[href='http://automationpractice.com/index.php?controller=my-account']")).getText();
	String expected = firstName + " " + lastName;
	System.out.println(actual);
	System.out.println(expected);
	Assert.assertEquals(actual, expected);
	
	
	
	}
}
