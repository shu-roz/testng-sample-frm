package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.GeneralUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class a013_DataProviderAnnoForDataDrivenTesting_UsingExternalFile {


    WebDriver driver;

    @BeforeClass
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
        driver.get("https://www.petfinder.com/");

    }

    @AfterMethod
    public void teardownMethod(){
        driver.quit();
    }



    @Test (dataProvider = "provideDataMethod")
    public void searchTestDataDriven(String searchTerm, String zipcode) throws InterruptedException {

        driver.findElement(By.id("simpleSearchAnimalType")).sendKeys(searchTerm);
        driver.findElement(By.id("simpleSearchLocation")).sendKeys(zipcode);
        driver.findElement(By.xpath("//div[@class='iconSearch-container']//button")).click();
        Thread.sleep(3000L);
        Assert.assertTrue(driver.getPageSource().contains(searchTerm));
    }

    @DataProvider
    public static Object[][] provideDataMethod(){
        return GeneralUtils.readFromCsvAs2DArray("randomNames.csv");
    }





}
