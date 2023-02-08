package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class a007_DependencyOfOneTConAnotherTC {


    //HARD DEPENDENCY:
    //if the dependor fails and the dependee TC is NOT marked as (alwaysRun=true), it defaults to an implicit (alwaysRun=false), and the depending TC will report as a SKIP, not a fail.

    //SOFT DEPENDENCY:
    //if the dependor fails and the dependee TC is set to (alwaysRun=true), then the dependent test will run either way and might report as a FAIL


    //generally, it is not advised to create any dependencies between TC's but if you absolutely have to this options is there.
    //a good candidate for this is - if it doesn't make sense to run a Test unless another Test passes, then you can choose this. For example, if a login TC fails, surely it makes no sense to continue with TC's that are based on being logged in)
    // Suppose, in a GWIN case, if we don't see a balance in a TC, then what's the point of continuing with a TC that tests if we can pay against that non-existent balance? that would be a candidate.



    // But generally, keep TC's independent - easier to maintain as the framework scales




    //Dependency originator TC, set to fail on purpose:
    @Test
    public void navigateToFindACat(){
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.get("https://www.petfinder.com/search/cats-for-adoption/us/ca/san-diego/");
//        Assert.assertTrue(driver.getPageSource().contains("cat"));
        Assert.assertTrue(driver.getPageSource().contains("GECKO Lizard"));
        driver.quit();
    }



    //HARD DEPENDENCY:  if "navigateToFindCat" fails, this one will SKIP
    @Test (dependsOnMethods = {"navigateToFindACat"}, alwaysRun = false)
    public void testGetStartedWithAdoptionButton(){
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.get("https://www.petfinder.com/search/cats-for-adoption/us/ca/san-diego/");
        driver.findElement(By.xpath("//span[.='Get Started']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Let’s find the perfect match for you!"));
        driver.quit();
    }

    //SOFT DEPENDENCY: if "navigateToFindCat" fails, this one still Runs
    @Test (dependsOnMethods = {"navigateToFindACat"}, alwaysRun = true)
    public void testGetStartedWithAdoptionButton2(){
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.get("https://www.petfinder.com/search/cats-for-adoption/us/ca/san-diego/");
        driver.findElement(By.xpath("//span[.='Get Started']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Let’s find the perfect match for you!"));
        driver.quit();
    }





}
