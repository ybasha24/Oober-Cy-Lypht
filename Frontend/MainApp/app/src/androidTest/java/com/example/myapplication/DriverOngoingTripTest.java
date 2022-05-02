package com.example.myapplication;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.provider.ProviderProperties;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.driver.TripsAdapter;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DriverOngoingTripTest{
    private static final int SIMULATED_DELAY_MS = 2000;
    private LocationManager locationManager;
    private Handler mHandler;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void ongoingTripTest(){
        // Sign in
//        String email = "driver2@email.com";
//        String password = "abc";
//        onView(withId(R.id.usernameInput)).perform(typeText(email));
//        onView(withId(R.id.passwordInput)).perform(typeText(password));
//        onView(withId(R.id.passwordInput)).perform(closeSoftKeyboard());
//        onView(withId(R.id.loginButton)).perform(click());
//        try { Thread.sleep(SIMULATED_DELAY_MS); } catch (InterruptedException e) { }

        // View all my trips
        onView(withId(R.id.driverRidesButton)).perform(click());

        // View first trip
        onView(withText("View")).perform(click());

        // Start the trip
        onView(withText("Start")).perform(click());

                locationManager = (LocationManager) InstrumentationRegistry.getInstrumentation().getTargetContext().getSystemService(Context.LOCATION_SERVICE);

                String mockLocationProvider = LocationManager.GPS_PROVIDER;
                locationManager.addTestProvider(mockLocationProvider, false, false, false, false, false, true, true, 0, 1);
                locationManager.setTestProviderEnabled(mockLocationProvider, true);
                Location loc = new Location(mockLocationProvider);
                Location mockLocation = new Location(mockLocationProvider);

                // Pick up rider at Ames Library
                mockLocation.setLatitude(42.0625);
                mockLocation.setLongitude(-93.6125);
                mockLocation.setAccuracy(1);
                locationManager.setTestProviderLocation( mockLocationProvider, mockLocation);

                try { Thread.sleep(5000); } catch (InterruptedException e) { }

                mockLocation.setLatitude(42.0023);
                mockLocation.setLongitude(-93.6141);
                mockLocation.setAccuracy(1);
                locationManager.setTestProviderLocation( mockLocationProvider, mockLocation);

                try { Thread.sleep(5000); } catch (InterruptedException e) { }


    }

}
