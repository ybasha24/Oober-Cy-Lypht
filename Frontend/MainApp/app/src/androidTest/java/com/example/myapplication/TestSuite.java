package com.example.myapplication;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterTest.class,
        SignInUserTest.class,
        DeleteUserTest.class
})
public class TestSuite {
}
