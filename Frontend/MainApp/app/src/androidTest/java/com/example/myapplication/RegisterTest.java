package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterTest {
    private static final int SIMULATED_DELAY_MS = 5000;
    public static int createdRiderId = 0;
    public static int createdDriverId = 0;
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testRegisterDriver(){
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.profileUpdate)).perform(click());
        onView(withId(R.id.editTextFirstName)).perform(typeText("driver"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("last"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("driver@email.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("Password123!"), closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(typeText("515-123-1234"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextCity)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextState)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextZip)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.registerDriverButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        try { createdDriverId = MainActivity.accountObj.getInt("id"); }catch(Exception e){}
        onView(withText("Sign out")).check(matches(isDisplayed()));

    }

    @Test
    public void testRegisterRider(){
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.profileUpdate)).perform(click());
        onView(withId(R.id.editTextFirstName)).perform(typeText("rider"), closeSoftKeyboard());
        onView(withId(R.id.editTextLastName)).perform(typeText("last"), closeSoftKeyboard());
        onView(withId(R.id.editTextEmail)).perform(typeText("rider@email.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("Password123!"), closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(typeText("515-123-1234"), closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextCity)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextState)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editTextZip)).perform(typeText("12345"), closeSoftKeyboard());
        onView(withId(R.id.registerDriverButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        try { createdRiderId = MainActivity.accountObj.getInt("id"); }catch(Exception e){}
        onView(withText("Sign out")).check(matches(isDisplayed()));
    }
}