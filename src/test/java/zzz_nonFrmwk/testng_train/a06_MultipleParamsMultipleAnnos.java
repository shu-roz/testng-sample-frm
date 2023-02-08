package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class a06_MultipleParamsMultipleAnnos {

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



    @Test (priority = -1)
    public void loginTest1(){
        driver.findElement(By.xpath("//*[.='Log In']")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.petfinder.com/user/login/"));
    }


    //one or the other is enough (enabled=false or @Ignore, to disable a TC from showing up on a Report, but this is just to demo that we can use multiple Annos and multiple Params
    @Ignore
    @Test (priority = 2, enabled = false)
    public void loginTest2(){
        Assert.assertFalse(driver.getTitle().equals("Urgent Need for Pet Adoption - Find Dogs & Cats & More | Petfinder"));
    }







}
