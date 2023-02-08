package zzz_nonFrmwk.testng_train;

import org.testng.annotations.Test;

public class a008_timeoutAnnoParam {




    //if a test takes longer than a certain predetermined duration of time, tests are stopped and reported as a fail.

    //there is also a way to set a Timeout on a Framework-global level - within the TestNG xml files.

    //this is a good proactive mechanism to make sure that your campaign run isn't going for 20 hours because of some faulty code/infinite loop situation




    @Test(timeOut = 500L)
    public void timeOutTestDemo() throws InterruptedException {
//        Thread.sleep(1000L);
        System.out.println("Timeout test demo");
    }




//the failure report shows a thrown TestNG exception: ThreadTimeoutException: Method didn't finish within the time-out of 500 milliseconds





}
