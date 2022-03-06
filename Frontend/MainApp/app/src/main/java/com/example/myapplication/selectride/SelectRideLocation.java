package com.example.myapplication.selectride;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.myapplication.app.AppController;
import com.example.myapplication.driver.DriverHomePage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class SelectRideLocation extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AutocompleteSupportFragment autocompleteOriginFragment;
    AutocompleteSupportFragment autocompleteDestFragment;
    static public LatLng origin;
    static public LatLng dest;
    static public String originString;
    static public String destString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ride_location);

        origin = null;
        dest = null;

        Places.initialize(getApplicationContext(), "AIzaSyDmvxGMTWWetUCbk92F4hcCjNtY-0UhyaM");

        autocompleteOriginFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_origin_fragment);
        autocompleteOriginFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteOriginFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.d("Maps", "Place: " + place.getName() + ", " + place.getId());
                origin = place.getLatLng();
                originString = place.getName();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(origin.latitude, origin.longitude), 12.0f));
                mMap.addMarker(new MarkerOptions().position(origin));
            }
            @Override
            public void onError(@NonNull Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        autocompleteDestFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_dest_fragment);
        autocompleteDestFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteDestFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Log.d("Maps", "Place: " + place.getName() + ", " + place.getId());
                dest = place.getLatLng();
                destString = place.getName();
                mMap.addMarker(new MarkerOptions().position(dest));
                if (origin != null && dest != null)
                    drawRoute();
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.d("Maps", "Error: " + status);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void drawRoute(){
        String routeOrigin = "origin=" + originString;
        String waypoints = "";
        String routeDest = "destination=" + destString;
        String params = routeOrigin + "&" + waypoints + "&"  + routeDest + "&key=" + "AIzaSyDmvxGMTWWetUCbk92F4hcCjNtY-0UhyaM";
        String url = "https://maps.googleapis.com/maps/api/directions/json?" + params;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null,
            response -> {

                try {
                    JSONArray routeArray = response.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);
                    JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                    String encodedString = overviewPolylines.getString("points");
                    List<LatLng> list = PolyUtil.decode(encodedString);

                    mMap.addPolyline(new PolylineOptions()
                        .addAll(list)
                        .width(12)
                        .color(Color.parseColor("#05b1fb"))//Google maps blue color
                        .geodesic(true)
                    );

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 10.0f));
                    calculateRoute();
                }
                catch(JSONException e){
                }
            },
            error -> {

            });
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }

    public void calculateRoute(){

        String routeOrigin = "origins=" + originString;
        String routeDest = "destinations=" + destString;
        String params = routeOrigin + "&"  + routeDest + "&units=imperial" + "&key=" + "AIzaSyDmvxGMTWWetUCbk92F4hcCjNtY-0UhyaM";
        params = params.replaceAll(" ", "%20");
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?" + params;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null,
                response -> {
                    try {
                        JSONObject rows0 = response.getJSONArray("rows").getJSONObject(0);
                        JSONArray elements = (JSONArray) rows0.get("elements");
                        String distance = elements.getJSONObject(0).getJSONObject("distance").getString("text");
                        String duration = elements.getJSONObject(0).getJSONObject("duration").getString("text");
                        Log.e("Error", distance + " " + duration);
                    }
                    catch(JSONException e){
                    }
                },
                error -> {});
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }

    public void proceed(View v){
        Intent i = new Intent(this, ConfirmRide.class);
        startActivity(i);
    }

}
