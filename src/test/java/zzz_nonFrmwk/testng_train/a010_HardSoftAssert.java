package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class a010_HardSoftAssert {


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



    //Hard Assert:
    @Test
    public void basicVerificationTest(){
        System.out.println("First Hard Assertion");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.petfinder.com/XXX"));  //deliberately failing to show the behavior of Hard Assert
        System.out.println("Second Hard Assertion");
        Assert.assertEquals(driver.getTitle(), "Urgent Need for Pet Adoption - Find Dogs & Cats & More | Petfinde");
        System.out.println("Third Hard Assertion");
        List<WebElement> options = driver.findElements(By.xpath("//a[.='About Pet Adoption']//ancestor::div[@class='menu-inner menu-level-1']//li[@class='menu-item menu-item--expanded']"));
        for (WebElement option : options) {
            Assert.assertTrue(option.isDisplayed());
        }
        System.out.println("All assertions have ran?");
    }



    //Soft Assert:
    @Test
    public void basicVerificationTest2() {

        SoftAssert softassert = new SoftAssert();
        System.out.println("First Soft Assertion");
        softassert.assertTrue(driver.getCurrentUrl().equals("https://www.petfinder.com/XXX"));   //deliberately failing to show the behavior of Soft Assert
        System.out.println("Second Soft Assertion");
        softassert.assertEquals(driver.getTitle(), "Urgent Need for Pet Adoption - Find Dogs & Cats & More | Petfinder");
        System.out.println("Third Soft Assertion");
        List<WebElement> options = driver.findElements(By.xpath("//a[.='About Pet Adoption']//ancestor::div[@class='menu-inner menu-level-1']//li[@class='menu-item menu-item--expanded']"));
        for (WebElement option : options) {
            softassert.assertTrue(option.isDisplayed());
        }
        softassert.assertAll();
        System.out.println("All assertions have ran?");
    }


}
