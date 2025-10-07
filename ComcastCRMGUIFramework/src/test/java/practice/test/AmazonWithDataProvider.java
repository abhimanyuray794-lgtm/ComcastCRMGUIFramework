package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class AmazonWithDataProvider {
	@Test(dataProvider = "sendData")

	public void getData(String brand, String productName) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand, Keys.ENTER);
		System.out.println(productName);

		WebElement element = driver.findElement(By.xpath("//span[text()='" + productName
				+ "']/ancestor::div[@class=\"puisg-col-inner\"]//span[@class='a-price-whole']"));
		String price = element.getText();
		System.out.println(price);

		driver.quit();

	}

	@DataProvider
	public Object[][] sendData() throws EncryptedDocumentException, IOException {

		ExcelUtility eLib = new ExcelUtility();

		int rowCount = eLib.getRowCount("DataProvider");

		Object[][] obj = new Object[rowCount][2];

		for (int i = 0; i < rowCount; i++) {

			obj[i][0] = eLib.getDataFromExcel("DataProvider", i + 1, 0);

			obj[i][1] = eLib.getDataFromExcel("DataProvider", i + 1, 1);

		}
		return obj;
	}
}
