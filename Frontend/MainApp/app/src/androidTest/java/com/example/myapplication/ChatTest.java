package com.example.myapplication;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.provider.ProviderProperties;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.driver.TripsAdapter;
import com.example.myapplication.driver.completetrip.RateRider;
import com.example.myapplication.driver.completetrip.TripCompleted;
import com.example.myapplication.rider.completetrip.RateDriver;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChatTest {
    private static final int SIMULATED_DELAY_MS = 3000;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void driverChat() {
        // Sign in
        String email = "driver2@gmail.com";
        String password = "abc";
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }

        // View all my trips
        onView(withId(R.id.driverRidesButton)).perform(click());

        // View first trip
        onView(withText("View")).perform(click());

        // Chat with rider
        onView(withText("Chat")).perform(click());

        // Write and send message
        onView(withId(R.id.messageET)).perform(typeText("Hello rider. - Driver"), closeSoftKeyboard());
        onView(withId(R.id.sendMessageButton)).perform(click());
    }

    @Test
    public void riderChat() {
        // Sign in
        String email = "rider3@gmail.com";
        String password = "abc";
        onView(withId(R.id.usernameInput)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }


        // View all my trips -> trip has already started
        onView(withId(R.id.RiderTripsListButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }

        Intent i = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), RateDriver.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        InstrumentationRegistry.getInstrumentation().getTargetContext().startActivity(i);

        // Write review and submit
        onView(withId(R.id.riderCommentingDriverET)).perform(typeText("Driver review for rider"), closeSoftKeyboard());
        onView(withId(R.id.riderSubmitRatingButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }

        // Sign out
        onView(withId(R.id.signOutButton)).perform(click());

//        // Driver signs in
        onView(withId(R.id.usernameInput)).perform(typeText("driver2@gmail.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("abc"));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

//        // Driver views rating
        onView(withId(R.id.driverReviewsButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
    }
}
