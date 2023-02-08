package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class a011_ParametersAnnoForParametrization {

    //has to be used in conjunction with the appropriate XML file that utilizes a <parameters> tag
    //this works in pair with xml file:
    //                                  simple_parametrized_classrun.xml


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


    //non-parametrized, hardcoded version:
    @Test
    public void searchTestHardCoded() throws InterruptedException {
        String searchTerm = "gecko";

        driver.findElement(By.id("simpleSearchAnimalType")).sendKeys(searchTerm);
        driver.findElement(By.id("simpleSearchLocation")).sendKeys("92108");
        driver.findElement(By.xpath("//div[@class='iconSearch-container']//button")).click();
        Thread.sleep(3000L);
        Assert.assertEquals(driver.getTitle(), "Scales, Fins, & Others for Adoption | Petfinder");
    }



    //parametrized version:
    @Test
    @Parameters ("search_Word")     //has to match variable name provided in the Parametrized xml file (simple_parametrized_classrun.xml)
    public void searchTestParametrized(String searchTerm) throws InterruptedException {

        driver.findElement(By.id("simpleSearchAnimalType")).sendKeys(searchTerm);
        driver.findElement(By.id("simpleSearchLocation")).sendKeys("92108");
        driver.findElement(By.xpath("//div[@class='iconSearch-container']//button")).click();
        Thread.sleep(3000L);
        Assert.assertEquals(driver.getTitle(), "Scales, Fins, & Others for Adoption | Petfinder");
    }







}
