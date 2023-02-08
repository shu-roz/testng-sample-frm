package zzz_nonFrmwk.testng_train;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class a009_GroupsAnnoParam {


    @Test(groups={"smoke", "regression", "mini-regression"})
    public void groupAnnotationDemo(){
        System.out.println("@Test(groups={\"smoke\", \"regression\"}");
    }


//you define the group you want to run in XML file -> see "smoke_test_suite.xml" for syntax and organization of that


//The key thing to remember when using Groups parameter and arranging an XML file to run them is to set all the relevant @Before/@After annotations to (alwaysRun=true)
//the reason is that by default, when you run tests based on Group in XML, @BeforeSuite/@BeforeTest and @AfterSuite/@AfterTest-annotated
// methods don't run, unless you explicitly define with (alwaysRun=true)

//the below two will run regardless because they are in the same class as the TC itself, so they are bound to run regardless. But @BeforeSuite/Test, @AfterSuite/Test won't run by default.


    @BeforeMethod
    public void beforeTestActivitiesSuchAsOpeningAResource(){
        System.out.println("@BeforeSuite or @BeforeTest-annotated methods must be set to ALWAYSRUN=TRUE");
    }


    @AfterMethod
    public void afterTestActivitiesSuchAsClosingAResource(){
        System.out.println("@AfterSuite or @AfterTest-annotated methods must be set to ALWAYSRUN=TRUE, to not get omitted");
    }


}
