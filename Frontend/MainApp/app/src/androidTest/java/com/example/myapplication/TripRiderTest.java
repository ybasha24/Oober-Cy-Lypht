package com.example.myapplication;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.myapplication.rider.searchtrip.SearchTripPlace;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TripRiderTest {

    private static final int SIMULATED_DELAY_MS = 2000;
    public static int createdRiderId = 0;
    public static int createdDriverId = 0;
    private static String email = "rider@email.com";
    private static String password = "Password123!";

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void searchTrip(){
        onView(withId(R.id.usernameInput)).perform(typeText(email));
        onView(withId(R.id.passwordInput)).perform(typeText(password));
        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.findRideButton)).perform(click());
        onView(withId(R.id.selectTimeButton)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 15));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.selectDateButton)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2022,4,27));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.chooseStartButton)).perform(click());
        SearchTripPlace.originAddress = "2229 Lincoln Way, Ames, IA 50011, USA";
        SearchTripPlace.destAddress = "Des Moines, IA, USA";
        SearchTripPlace.distance = 59.079;
        SearchTripPlace.durationHours = 0;
        SearchTripPlace.durationMinutes = 40;
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.searchPageLocationButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.searchRideButton)).perform(click());
        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

}
