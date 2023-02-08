package zzz_nonFrmwk.testng_train;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class a05_DisablingATestCaseFromRunAndReport {


    //to ignore a test case, either:
    //              use     @Test(enabled=false)        anno
    //              or use  @Ignore @Test            anno

    @Test(enabled=false)
    public void aTest(){
        //XML or class Run ignores a TC when its anno is @Test(enabled=false).  Use this when you are working on a TC
        // and it is unfinished, not fully ready for a run in a campaign
    }



    @Ignore @Test
    public void bTest(){
        //XML or class Run ignores a TC when its anno is @Ignore.  Use this when you are working on a TC
        // and it is unfinished, not fully ready for a run in a campaign
    }




}
