package com.example.myapplication;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterTest.class,
        //HomePageTest.class,
        TripTestDriver.class,
        DeleteUserTest.class
})
public class TestSuite {
}
