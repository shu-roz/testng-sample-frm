package zzz_nonFrmwk.testng_train;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class a01_DemoMaven {

//    public static void main(String[] args) {
//
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.petfinder.com/");
//
//        Faker faker = new Faker();
//        System.out.println(faker.address().fullAddress());
//        System.out.println(faker.chuckNorris().fact());
//    }



    @Test
    public void testngTC01(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.petfinder.com/");
        Assert.assertTrue(driver.getTitle().equals("Urgent Need for Pet Adoption - Find Dogs & Cats & More | Petfinder"));
        driver.quit();
    }


}
