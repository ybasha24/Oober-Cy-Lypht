package com.example.myapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
//        HomePageTest.class,
//        TripTestDriver.class,
//        OngoingTripTest.class,
        RateUserTest.class,
//        ChatTest.class,

        // Always run these 2 together
//        RegisterTest.class,
//        Cleanup.class,

})
public class TestSuite {
}
