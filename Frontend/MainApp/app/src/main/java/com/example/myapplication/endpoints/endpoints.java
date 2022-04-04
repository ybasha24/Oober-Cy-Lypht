package com.example.myapplication.endpoints;

public final class endpoints {

    public static final String DriverRegUrl = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";
    public static final String RiderRegUrl = "http://coms-309-030.class.las.iastate.edu:8080/rider/registerRider/";
    public static final String LoginUrl = "http://coms-309-030.class.las.iastate.edu:8080/user/getUserSignIn?email=";
    public static final String EditUserUrl = "http://coms-309-030.class.las.iastate.edu:8080/user/editUser?id=";
    public static final String DeleteUserUrl = "http://coms-309-030.class.las.iastate.edu:8080/admin/deleteUser?id=";

    public static final String DriverCreateTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/createTripByDriver?driverId=";
    public static final String GoogleMapsDirectionUrl = "https://maps.googleapis.com/maps/api/directions/json?";
    public static final String GoogleMapsDistanceUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?";
    public static final String GoogleMapsAPIKey = "AIzaSyDmvxGMTWWetUCbk92F4hcCjNtY-0UhyaM";

}
