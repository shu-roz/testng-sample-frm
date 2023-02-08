package zzz_nonFrmwk.testng_train;

import org.testng.annotations.*;

public class a02_ConfigAnno {


    @BeforeSuite
    public void setupSuite(){
        System.out.println("BeforeSuite");
    }
    @AfterSuite
    public void teardownSuite(){
        System.out.println("AfterSuite");
    }



    //The <test> section of xml file could be used for grouping API tests and @BeforeTest could be used for setting baseURI, for example.
    //or for grouping UI vs DB vs API tests. If you use these, makes ure to organize your xml file in multiple <test>s within the Suite also.
    //<suite>
        //<test>
            //<classes>
                //<class> ... </class>
            //</classes>
        //</test>

        //<test>.... </test>
    //</suite>
    @BeforeTest
    public void setupTestGroup(){
        System.out.println("BeforeTest (group)");
    }
    @AfterTest
    public void teardownTestGroup(){
        System.out.println("AfterTest (group)");
    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass - runs once before all the methods of a class have ran.  This method has to be parked inside the utilizing class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass - runs once after all the methods of a class have ran.  This method has to be parked inside the utilizing class");
    }




    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeEachMethodInAClass - runs once before each method of a class runs.  This method has to be parked inside the utilizing class");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterEachMethodInAClass - runs once after each method of a class runs.  This method has to be parked inside the utilizing class");
    }



    @Test
    public void testCase01(){
        System.out.println("Test case 01");
    }

    @Test
    public void testCase02(){
        System.out.println("Test case 02");
    }


}
