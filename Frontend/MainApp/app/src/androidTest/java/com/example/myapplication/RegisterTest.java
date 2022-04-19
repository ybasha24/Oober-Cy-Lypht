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
    private static final int SIMULATED_DELAY_MS = 2000;
    public static int createdRiderId = 0;
    public static int createdDriverId = 0;
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

//    @Before
//    public void set() {
//        mainActivityRule.getScenario().onActivity(activity -> {
//            SharedPreferences.Editor editor = activity.getApplicationContext().getSharedPreferences("name", MODE_PRIVATE).edit();
//            editor.putString("email", "");
//            editor.putString("password", "");
//            editor.putBoolean("isLoggedIn", false);
//            editor.apply();
//        });
//    }
//    @Test
//    public void useAppContext() {
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.myapplication", appContext.getPackageName());
//    }

//    @Test
//    public void testLogin(){
//        String email = "a";
//        String password = "a";
//        onView(withId(R.id.usernameInput))
//                .perform(typeText(email), closeSoftKeyboard());
//        onView(withId(R.id.passwordInput))
//                .perform(typeText(password), closeSoftKeyboard());
//        onView(withId(R.id.loginButton)).perform(click());
//        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
//        onView(withText("Sign out")).check(matches(isDisplayed()));
//    }

    @Test
    public void testRegisterDriver(){
        onView(withId(R.id.registerButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.registeringDriverButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.editTextFirstName)).perform(typeText("first"));
        onView(withId(R.id.editTextLastName)).perform(typeText("last"));
        onView(withId(R.id.editTextEmail)).perform(typeText("driver@email.com"));
        onView(withId(R.id.editTextPassword)).perform(typeText("Password123!"));
        onView(withId(R.id.editTextPhone)).perform(typeText("515-123-1234"));
        onView(withId(R.id.editTextPhone)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("name"));
        onView(withId(R.id.editTextAddress)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextCity)).perform(typeText("name"));
        onView(withId(R.id.editTextCity)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextState)).perform(typeText("name"));
        onView(withId(R.id.editTextState)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextZip)).perform(typeText("12345"));
        onView(withId(R.id.editTextZip)).perform(closeSoftKeyboard());
        onView(withId(R.id.registerDriverButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        try {
            createdDriverId = MainActivity.accountObj.getInt("id");
        }catch(Exception e){}
        onView(withText("Sign out")).check(matches(isDisplayed()));
    }

    @Test
    public void testRegisterRider(){
        onView(withId(R.id.registerButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.registeringRiderButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.editTextFirstName)).perform(typeText("first"));
        onView(withId(R.id.editTextLastName)).perform(typeText("last"));
        onView(withId(R.id.editTextEmail)).perform(typeText("rider@email.com"));
        onView(withId(R.id.editTextPassword)).perform(typeText("Password123!"));
        onView(withId(R.id.editTextPhone)).perform(typeText("515-123-1235"));
        onView(withId(R.id.editTextPhone)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextAddress)).perform(typeText("name"));
        onView(withId(R.id.editTextAddress)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextCity)).perform(typeText("name"));
        onView(withId(R.id.editTextCity)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextState)).perform(typeText("name"));
        onView(withId(R.id.editTextState)).perform(closeSoftKeyboard());
        onView(withId(R.id.editTextZip)).perform(typeText("12345"));
        onView(withId(R.id.editTextZip)).perform(closeSoftKeyboard());
        onView(withId(R.id.registerRiderButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        try {
            createdRiderId = MainActivity.accountObj.getInt("id");
        }catch(Exception e){}
        onView(withText("Sign out")).check(matches(isDisplayed()));
    }
}