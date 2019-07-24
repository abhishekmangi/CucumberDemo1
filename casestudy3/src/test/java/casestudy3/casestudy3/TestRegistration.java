package casestudy3.casestudy3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRegistration {
	 WebDriver driver;
  @Test(priority=1)
  public void testRegistration() throws InterruptedException
  {
	  System.setProperty("webdriver.chrome.driver" ,"C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//a[contains(text(),'SignUp')]")).click();
	  WebElement uname = driver.findElement(By.xpath("//input[@name='userName']"));
	  uname.sendKeys("asdfgh");
	  driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Abhishek");
	  driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Mangi");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abhishek9848");
	  driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("Abhishek9848");
	  driver.findElement(By.xpath("//input[@value='Male']")).click();
	  driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("abc@gmail.com");
	  driver.findElement(By.xpath("//input[@name='mobileNumber']")).sendKeys("7995201304");
	  driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("22/06/1997");
	  driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("hahahahahahaahahhahahahaahahahahahahahha");
	  Thread.sleep(5000);
	  String v1 = "Available";
	  String v2 = driver.findElement(By.xpath("//span[text()='Available']")).getText();
	  Assert.assertEquals(v1, v2);
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  
  }
}
