package com.example.myapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterTest.class,
//        HomePageTest.class,
//        TripTestDriver.class,
//        OngoingTripTest.class,
//        RateUserTest.class,
//        ChatTest.class,

        // Always run this
        Cleanup.class,

})
public class TestSuite {
}
