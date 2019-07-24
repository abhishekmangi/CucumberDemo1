package casestudy3.casestudy3;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class OnlineShoppingTest2 {
	
	
WebDriver driver;
ExtentHtmlReporter htmlReporter;
ExtentReports extent;
ExtentTest test;


  @Test (priority = 1)
  public void testregistration() throws InterruptedException {
 
	  test = extent.createTest("TC001","report_testing");
	  driver = MyDriver123.getDriver("chrome");  
	  driver.get("http://10.232.237.143:443/TestMeApp");
	  driver.manage().window().maximize();
	  System.out.println("abhishekwwwwww");
	  
	  driver.findElement(By.xpath("//a[contains(text(),'SignUp')]")).click();
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("abhiishhe12kk");
	  driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("abhishek");
	  driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("mangi");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abcd1234");
	  driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("abcd1234");
	  driver.findElement(By.xpath("//span[text()='Male']")).click();
	  driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("abhi@gmail.com");
	  driver.findElement(By.xpath("//input[@name='mobileNumber']")).sendKeys("79952013014");
	  driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("15/07/1997"); 
	  driver.findElement(By.xpath("//textarea[@name='address']")).sendKeys("OYOLIFE GRG1094");
	  driver.findElement(By.xpath("//input[@id='answer']")).sendKeys("VIZAG");
	 
	 
	  Thread.sleep(5000);
	  
	  String verify1 ="Available";
	  String verify2 = driver.findElement(By.xpath("//span[text()='Available']")).getText();
	  Assert.assertEquals(verify1, verify2);
	  driver.findElement(By.xpath("//input[@value='Register']")).click();
	  String E_title = driver.getTitle();
	  String A_title ="Login";
	  Assert.assertEquals( A_title,E_title);
  }
  
 

@Test (priority = 2)
 public void testlogin() {
	test = extent.createTest("TC002","report_testing");
	driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("asdfgh");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("asdfgh");	
	driver.findElement(By.xpath("//input[@name='Login']")).click();
	String LoginVal = driver.findElement(By.xpath("//a[contains(text(),'SignOut')]")).getText();
	Assert.assertEquals("SignOut",LoginVal);

}



@Test (priority = 3)
 public void testCart() {
	test = extent.createTest("TC003","report_testing");
	driver.findElement(By.xpath("//input[@id='myInput']")).sendKeys("headphone");
	driver.findElement(By.xpath("//input[@value='FIND DETAILS']")).click();
	driver.findElement(By.xpath("//a[text()=' Add to cart']")).click(); 
	driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	String Avlproduct= driver.findElement(By.xpath("//h4[text()='Headphone']")).getText();
	Assert.assertEquals("Headphone",Avlproduct);

}	 



@Test (priority = 4)
 public void testPayment() throws InterruptedException {
	test = extent.createTest("TC004","report_testing"); 
	driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();
	driver.findElement(By.xpath("//textarea[@name='ShippingAdd']")).sendKeys("Sector 20 Gurugram");
	driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	
	
	Thread.sleep(20000);
	
	
	driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label/i")).click();
	driver.findElement(By.xpath("//a[text()='Continue']")).click();
	driver.findElement(By.xpath("//input[@name='username']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pass@456");
	driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	driver.findElement(By.xpath("//input[@name='transpwd']")).sendKeys("Trans@456");
	driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	
	
	String E_title = driver.getTitle();
	String A_title ="Order Details";
	Assert.assertEquals( A_title,E_title);
	driver.close();
}



  @AfterMethod
  public void getResultaftermethod(ITestResult result) throws IOException {
	  
	if(result.getStatus()== ITestResult.FAILURE) 
	{
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"FAILED",ExtentColor.RED));
		TakesScreenshot snapshot = (TakesScreenshot)driver;
		File src = snapshot.getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir")+"/test-output/screens/"+result.getName()+".png";
		FileUtils.copyFile(src, new File(Path));
		test.addScreenCaptureFromPath(Path,result.getName());
		test.fail(result.getThrowable());
	}
	else if (result.getStatus()== ITestResult.SUCCESS)
	{
	test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"PASSED",ExtentColor.GREEN));
	}
	else 
	{
	test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"SKIPPED",ExtentColor.ORANGE));
	test.skip(result.getThrowable());
	}

  }

  
  
  @BeforeTest
  public void startReportbeforetest()
  {
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	 extent = new ExtentReports();
	 extent.attachReporter(htmlReporter);
	 htmlReporter.config().setDocumentTitle("Extent Report Demo");
	 htmlReporter.config().setReportName("Test Report");
	 htmlReporter.config().setTheme(Theme.STANDARD);
	 htmlReporter.config().setTimeStampFormat("EEEE,MMMM dd,yyyy,hh:mm a '('zzz')'");
	 
  }

  
  
  @AfterTest
  public void endreportafterTest()
  {
	  extent.flush();
  }

}
