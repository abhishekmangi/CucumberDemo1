package casestudy3.casestudy3;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class OnlineShoppingTest {
	
	WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	 @Test(priority=1)
	 public void testRegistration() throws InterruptedException
	 {
	 test = extent.createTest("TC001","report_testing");
	 System.setProperty("webdriver.chrome.driver" ,"C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//a[contains(text(),'SignUp')]")).click();
	  WebElement uname = driver.findElement(By.xpath("//input[@name='userName']"));
	  uname.sendKeys("krkrkrkrkrrkrkrkk");
	  driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Abhiiiiiiiii");
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
	
	
	
	
	
	
//	String left = driver.getTitle();
//	String right = "Heft4";
//	Assert.assertEquals(left,right);
//	driver.close();
	  }
	 
	 @Test(priority=2)
	 public void testLogin() throws InterruptedException
	 {
		 WebElement uname = driver.findElement(By.id("userName"));
		 WebElement password = driver.findElement(By.name("password"));
		 WebElement submit = driver.findElement(By.name("Login"));
		 uname.sendKeys("Abhishek");
		 password.sendKeys("Abhishek9848");
		 submit.click();
		 String E_title = driver.getTitle();
		 String A_title = "Home";
		 Assert.assertEquals(E_title,A_title);
	 }
	
	 @Test(priority=3)
	  public void testCart()
	  {
	 driver.findElement(By.xpath("//input[@id='myInput']")).sendKeys("headphone");
	 driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();
	 driver.findElement(By.xpath("//a[@href='#']")).click();
	 driver.findElement(By.xpath("//a[@href='displayCart.html']")).click();
	 String Avlproduct=driver.findElement(By.xpath("//h4[text()='Headphone']")).getText();
	 Assert.assertEquals("Headphone",Avlproduct);
	  }
	 
	@AfterMethod
	  public void getResultAfterMethod(ITestResult result) throws IOException {
	if(result.getStatus()== ITestResult.FAILURE) {
	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"FAILED",ExtentColor.RED));
	TakesScreenshot snapshot = (TakesScreenshot)driver;
	File src = snapshot.getScreenshotAs(OutputType.FILE);
	String Path = System.getProperty("user.dir")+"/test-output/screens/"+result.getName()+".png";
	FileUtils.copyFile(src, new File(Path));
	test.addScreenCaptureFromPath(Path,result.getName());
	test.fail(result.getThrowable());
	}
	else if (result.getStatus()== ITestResult.SUCCESS) {
	test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"PASSED",ExtentColor.GREEN));
	}
	else {
	test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"SKIPPED",ExtentColor.ORANGE));
	test.skip(result.getThrowable());
	}
	  }
	
	@BeforeTest
	  public void startReportBeforeTest() {
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	 extent = new ExtentReports();
	 extent.attachReporter(htmlReporter);
	 htmlReporter.config().setDocumentTitle("Extent Report Demo");
	 htmlReporter.config().setReportName("Test Report");
	 htmlReporter.config().setTheme(Theme.STANDARD);
	 htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd,yyyy,hh:mm a '('zzz')'");
	  }

	  @AfterTest
	  public void endReportAfterTest() {
	 extent.flush();
	  }
	

	 
	 
}
