package zzz_nonFrmwk.testng_train;

import org.testng.annotations.Test;

public class a04_NaturalPrioritizationTestNg {


    //just remember that in TestNG, naturally, all of the @Test-annotated cases are prioritized according to alphabetical
    // order, default prioroty of execution is 0 for all of them.  They don't run in the same order as you arrange them in a class.
    //undefined (with a priority parameter) TC's are assumed to have the priority of 0. If you want to prioritize runs,
    //utilize the (priority = -1), (priority = 0) @Test anno parameters.  Unless specified, priority of all the un-annotated
    // TC's is set to equal - 0, and they will run according to alphabetical order of TC/method name.
    //priority annotation runs it from smallest to largest priority TC's in order.
    //for example, we might have to run GWIN cases before anything else, so this can be utilized.


    @Test (priority = 0)
    public void enterPassword(){
        System.out.println("enter password TC");
    }

    //priority is 0, because un-prioritized/un-annotated and competes with the above TC and orders this and above in alphabetical order.
    @Test
    public void zoomIn(){
        System.out.println("zoom in TC");
    }

    @Test (priority = -1)
    public void verifyUrl(){
        System.out.println("verifyUrl");
    }


    @Test(priority = 1)
    public void clickOnAPet(){
        System.out.println("clickOnAPet");
    }

}
