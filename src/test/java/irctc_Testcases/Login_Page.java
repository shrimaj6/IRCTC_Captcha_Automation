package irctc_Testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Login_Page {
	public static WebDriver driver;

	@BeforeTest
	public void Driver() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void NewUser_Registration() throws InterruptedException, IOException, TesseractException {
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.xpath("//a[@aria-label='Click here to Login in application']")).click();
		driver.findElement(By.xpath("//input[@placeholder='User Name']")).sendKeys("spmehta60");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("8411824096@Shri");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='nlpInputContainer']//div[@title='Refresh:I need another Option']"))
				.click();
		Thread.sleep(3000);
		WebElement imgcap = driver.findElement(By.xpath("//img[@id='nlpCaptchaImg']"));

		File src = imgcap.getScreenshotAs(OutputType.FILE);
		String path = "C:\\Users\\shrim\\eclipse-workspace\\IRCTC_Captcha\\Captcha_ScreenShot\\captcha.png";
		FileHandler.copy(src, new File(path));
		Tesseract img = new Tesseract();
		img.setDatapath("D:\\driver\\Tess4J\\tessdata");
		String str = img.doOCR(new File(path));
		System.out.println(str);

		driver.findElement(By.xpath("//input[@id='nlpAnswer']")).sendKeys(str);
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//button[text()='SIGN IN']")).click();
	}
}