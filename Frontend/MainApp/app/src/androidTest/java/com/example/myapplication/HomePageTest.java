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

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomePageTest {
    private static final int SIMULATED_DELAY_MS = 2000;
    public static int createdRiderId = 0;
    public static int createdDriverId = 0;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void driverProfileTest(){
        String email = "driver@email.com";
        String password = "Password123!";
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.profileUpdate)).perform(click());
        onView(withText("Save Changes")).check(matches(isDisplayed()));
        onView(withId(R.id.editTextFirstName2)).perform(typeText("Steve"));
        onView(withId(R.id.editTextFirstName2)).perform(closeSoftKeyboard());
        onView(withId(R.id.saveChangesButton)).perform(click());
        onView(withText("Save Changes")).check(matches(isDisplayed()));
    }

    @Test
    public void riderProfileTest(){
        String email = "rider@email.com";
        String password = "Password123!";
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.profileUpdate)).perform(click());
        onView(withText("Save Changes")).check(matches(isDisplayed()));
        onView(withId(R.id.editTextFirstName2)).perform(typeText("Steve"));
        onView(withId(R.id.editTextFirstName2)).perform(closeSoftKeyboard());
        onView(withId(R.id.saveChangesButton)).perform(click());
        onView(withText("Save Changes")).check(matches(isDisplayed()));
    }

    @Test
    public void signOutRiderTest(){
        String email = "rider@email.com";
        String password = "Password123!";
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.signOutButton)).perform(click());
        onView(withText("Sign In")).check(matches(isDisplayed()));
    }

    @Test
    public void signOutDriverTest(){
        String email = "driver@email.com";
        String password = "Password123!";
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.signOutButton)).perform(click());
        onView(withText("Sign In")).check(matches(isDisplayed()));
    }

}
