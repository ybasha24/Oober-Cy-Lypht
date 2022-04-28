package com.example.myapplication.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.app.AppController;
import com.example.myapplication.endpoints.Endpoints;
import com.example.myapplication.endpoints.OtherConstants;
import com.google.android.gms.location.FusedLocationProviderClient;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.*;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class OngoingTrip extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private CameraPosition cameraPosition;
    private PlacesClient placesClient;

    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    private Location lastKnownLocation;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private LinkedHashMap<String, String> origins;
    private LinkedHashMap<String, String> destinations;

    private TextView driverInstructionsTV;
    private String currentGoalString;
    private Address currentGoalAddress;
    private Location currentGoalLocation;
    private String currentGoalRider;
    private Geocoder geocoder;

    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        setContentView(R.layout.activity_driver_ongoing_trip);

        driverInstructionsTV = findViewById(R.id.driverInstructionsTV);
        origins = new LinkedHashMap<>();
        destinations = new LinkedHashMap<>();

        geocoder = new Geocoder(this, Locale.getDefault());
        currentGoalLocation = new Location("");

        setRiderLocations();
        connect();

        Places.initialize(getApplicationContext(), OtherConstants.GoogleMapsAPIKey);
        placesClient = Places.createClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //major class that details what is done when location changes
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                sendLocation(longitude, latitude);

                try {
                    currentGoalAddress = geocoder.getFromLocationName(currentGoalString, 1).get(0);
                }catch(Exception e){}
                Log.e("error", currentGoalString);
                Log.e("error", currentGoalAddress.toString());
                double goalLat = currentGoalAddress.getLatitude();
                double goalLong = currentGoalAddress.getLongitude();

                currentGoalLocation.setLatitude(goalLat);
                currentGoalLocation.setLongitude(goalLong);

                if(location.distanceTo(currentGoalLocation) < 100){
                    if(origins.size() > 0){
                        origins.remove(currentGoalRider);
                    }
                    else if(destinations.size() > 0){
                        destinations.remove(currentGoalRider);
                    }
                    setNextGoal();
                }
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), DEFAULT_ZOOM));
                drawRoute(latitude, longitude, goalLat, goalLong);
                Log.e("error", "location change: " + latitude + " " + longitude + " | dist : " + currentGoalLocation.toString());
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }

    public void onMapReady(GoogleMap map) {
        this.map = map;
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
    }

    private void getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        lastKnownLocation = task.getResult();
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        }
                    } else {
                        Log.e("error", "Current location is null. Using defaults.");
                        Log.e("error", "Exception: %s", task.getException());
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                        map.getUiSettings().setMyLocationButtonEnabled(false);
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void setRiderLocations(){
        try{
            JSONObject trip = TripsAdapter.currentTrip;
            int tripId = trip.getInt("id");
            String url = Endpoints.GetRiderStops + tripId;
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject obj = response.getJSONObject(i);
                            int riderId = obj.getInt("riderId");
                            String origin = obj.getString("riderOriginAddress");
                            String destination = obj.getString("riderDestAddress");
                            origins.put(TripDetail.idToNameMap.get(riderId), origin);
                            destinations.put(TripDetail.idToNameMap.get(riderId), destination);
                            setNextGoal();
                        }
                        Log.e("error", origins.toString());
                        Log.e("error", destinations.toString());
                    }
                    catch(Exception e){
                        Log.e("error", e.toString());
                    }
                },
                error -> { }
            );
            AppController.getInstance().addToRequestQueue(req, "json_array_req");

            
        }catch(Exception e){}
    }

    private void setNextGoal(){
        if(origins.size() > 0) {
            for (String rider : origins.keySet()) {
                driverInstructionsTV.setText("Pick up " + rider + " at " + origins.get(rider));
                currentGoalRider = rider;
                currentGoalString = origins.get(rider);
            }
        }
        else if(destinations.size() > 0) {
            for (String rider : destinations.keySet()) {
                driverInstructionsTV.setText("Drop off " + rider + " at " + destinations.get(rider));
                currentGoalRider = rider;
                currentGoalString = destinations.get(rider);
            }
        }
    }

    private void drawRoute(double currentOrigin, double currentLatitute, double goalOrigin, double goalLatitute){
        String routeOrigin = "origin=" + currentOrigin + "," + currentLatitute;
        String waypoints = "";
        String routeDest = "destination=" + goalOrigin + "," + goalLatitute;
        String params = routeOrigin + "&" + waypoints + "&"  + routeDest + "&key=" + OtherConstants.GoogleMapsAPIKey;
        String url = Endpoints.GoogleMapsDirectionUrl + params;

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null,
                response -> {
                    try {
                        JSONArray routeArray = response.getJSONArray("routes");
                        JSONObject routes = routeArray.getJSONObject(0);
                        JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                        String encodedString = overviewPolylines.getString("points");
                        List<LatLng> list = PolyUtil.decode(encodedString);
                        map.addPolyline(new PolylineOptions().addAll(list).width(12).color(Color.parseColor("#05b1fb")).geodesic(true));
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentOrigin, currentLatitute), 10.0f));
                    }
                    catch(JSONException e){ Log.e("Maps error", e.toString()); }
                },
                error -> Log.e("Maps error", error.toString())
        );
        AppController.getInstance().addToRequestQueue(req, "obj_req");
    }

    private void connect(){

        Draft[] drafts = {new Draft_6455()};
        String url = "ws://localhost:8080/location/{" + MainActivity.accountEmail + "}";

        try {
            Log.e("error", "Trying socket");
            cc = new WebSocketClient(new URI(url), drafts[0]) {
                @Override
                public void onMessage(String message) { }
                @Override
                public void onOpen(ServerHandshake handshake) { }
                @Override
                public void onClose(int code, String reason, boolean remote) { }
                @Override
                public void onError(Exception e) { Log.e("error:", e.toString()); }
            };
        } catch (URISyntaxException e) { Log.e("error:", e.getMessage()); }
        cc.connect();
    }

    private void sendLocation(double a, double b){
        //ideally add email
        cc.send(a + ":" + b);
    }


}