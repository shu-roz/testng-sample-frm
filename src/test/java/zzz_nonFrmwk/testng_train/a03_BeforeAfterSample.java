package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class a03_BeforeAfterSample {

    WebDriver driver;

    @Test(alwaysRun = true)
    public void testCase01(){
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.petfinder.com/"));
    }

    @Test(alwaysRun = true)
    public void testCase02(){
        Assert.assertTrue(driver.getTitle().equals("Urgent Need for Pet Adoption - Find Dogs & Cats & More | Petfinder"));
    }


    @BeforeClass(alwaysRun = true)
    public void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod(){
        driver = new ChromeDriver();
        driver.get("https://www.petfinder.com/");
    }

    @AfterMethod (alwaysRun = true)
    public void teardownMethod(){
        driver.quit();
    }



}
