package com.example.myapplication.selectride;

import com.example.myapplication.R;
import com.example.myapplication.endpoints.endpoints;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

public class SelectRideLocation extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AutocompleteSupportFragment autocompleteOriginFragment;
    AutocompleteSupportFragment autocompleteDestFragment;
    LatLng origin;
    LatLng dest;
    static public String originString;
    static public String destString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ride_location);

        Places.initialize(getApplicationContext(), endpoints.MapAPIKey);

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
        List<LatLng> path = new ArrayList();
        GeoApiContext context = new GeoApiContext.Builder().apiKey(endpoints.MapAPIKey).build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context,
                origin.latitude + ", " + origin.longitude,
                dest.latitude + ", " + dest.longitude);
        try {
            DirectionsResult res = req.await();

            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e("Maps", ex.getLocalizedMessage());
        }

        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(6);
            mMap.addPolyline(opts);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 10.0f));
    }

    public void proceed(View v){
        Intent i = new Intent(this, ConfirmRide.class);
        startActivity(i);
    }

}
