package com.example.myapplication.endpoints;

/**
 * commonly used request Endpoints in the app
 */
public final class Endpoints {

    /**
     * where drivers register an account
     */
    public static final String DriverRegUrl = "http://coms-309-030.class.las.iastate.edu:8080/driver/registerDriver/";

    /**
     * where riders register an account
     */
    public static final String RiderRegUrl = "http://coms-309-030.class.las.iastate.edu:8080/rider/registerRider/";

    /**
     * where users sign in
     */
    public static final String LoginUrl = "http://coms-309-030.class.las.iastate.edu:8080/user/getUserSignIn?email=";

    /**
     * where users edit their account
     */
    public static final String EditUserUrl = "http://coms-309-030.class.las.iastate.edu:8080/user/editUser?id=";

    /**
     * where admins can delete a user
     */
    public static final String DeleteUserUrl = "http://coms-309-030.class.las.iastate.edu:8080/admin/deleteUser?id=";

    /**
     * where trips can be deleted
     */
    public static final String DeleteTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/deleteTripById";

    /**
     * where a driver can create a trip
     */
    public static final String DriverCreateTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/createTripByDriver?driverId=";

    /**
     * where a trip can be edited
     */
    public static final String EditTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/editTrip";

    /**
     * where all active trips from a rider a shown
     */
    public static final String AllDriverTripsUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/getAllActiveTripsFromDriverId?driverId=";

    /**
     * where a rider searches for a trip based on start date
     */
    public static final String RiderSearchTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/getTripsForRider?scheduledStartDate=";

    /**
     * where a rider is added to a trip
     */
    public static final String AddRiderToTripUrl = "http://coms-309-030.class.las.iastate.edu:8080/trip/addRiderToTrip?tripId=";

    /**
     * where directions from place A to place B are generated
     */
    public static final String GoogleMapsDirectionUrl = "https://maps.googleapis.com/maps/api/directions/json?";

    /**
     * where the distance from place A to place B is calculated
     */
    public static final String GoogleMapsDistanceUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?";

    /**
     * key for using Google Maps API
     */
    public static final String GoogleMapsAPIKey = "AIzaSyDmvxGMTWWetUCbk92F4hcCjNtY-0UhyaM";

    /**
     * where users change their profile picture
     */
    public static final String SetProfilePictureUrl = "http://coms-309-030.class.las.iastate.edu:8080/user/userId?id=";


}
