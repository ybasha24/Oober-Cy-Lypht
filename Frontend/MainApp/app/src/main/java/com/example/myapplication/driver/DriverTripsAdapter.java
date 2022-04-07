package com.example.myapplication.driver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.*;
import com.example.myapplication.app.AppController;
import com.example.myapplication.createride.SelectRideTime;
import com.example.myapplication.endpoints.endpoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * adapter class that shows all the trips of a driver
 */
public class DriverTripsAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;

    /**
     * creates a DriverTripsAdapter object
     * @param list list of trips
     * @param context context to put the list on
     */
    public DriverTripsAdapter(JSONArray list, Context context) {
        if(list == null){
            this.list = null;
        }
        else {
            this.list = list;
        }
        this.context = context;
    }

    /**
     * get size of list
     * @return size of list
     */
    @Override
    public int getCount() {
        return list.length();
    }

    /**
     * gets Object in list at specificed position
     * @param pos position of object
     * @return object as position pos
     */
    @Override
    public Object getItem(int pos) {
        try {
            return list.get(pos);
        }
        catch(Exception e){ return new Object(); }
    }

    /**
     * returns 0
     * @param pos unused variable
     * @return 0
     */
    @Override
    public long getItemId(int pos) {
        return 0;
    }

    /**
     * describes how the list elements are displayed
     * @param position position in list
     * @param convertView convertView object
     * @param parent parent for this view
     * @return the created View object
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(list != null) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.trip_item, null);
            }

            TextView tv = view.findViewById(R.id.textView);
            try {
                JSONObject json = list.getJSONObject(position);
                Log.e("Json logging", json.toString());

                tv.setText("From: " + json.getString("originAddress") + "\nTo: " + json.getString("destAddress") +
                        "\nTime: " + json.getString("scheduledStartDate") + "\n->" + json.getString("scheduledEndDate"));
            } catch (Exception e) {
            }

            Button editTripButton = view.findViewById(R.id.editTripButton);
            Button deleteTripButton = view.findViewById(R.id.deleteTripButton);

            editTripButton.setOnClickListener(v -> editTrip(position));

            deleteTripButton.setOnClickListener(v -> deleteTrip(position));

            return view;
        }
        return null;
    }

    /**
     * edits a trip
     * @param position position of the trip in the list
     */
    public void editTrip(int position){
        Intent i = new Intent(this.context, SelectRideTime.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            i.putExtra("editing", true);
            i.putExtra("tripId", list.getJSONObject(position).getInt("id"));
        }
        catch(JSONException e) {}
        this.context.startActivity(i);
    }

    /**
     * deletes a trip
     * @param position position of the trip in the list
     */
    public void deleteTrip(int position){
        try {
            StringRequest req = new StringRequest(Request.Method.DELETE, endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"),
                response -> {
                    Intent i = new Intent(this.context, DriverCreatedRides.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.context.startActivity(i);
                    Toast toast = Toast.makeText(this.context, "Successfully deleted trip", Toast.LENGTH_LONG);
                    toast.show();
                },
                error -> {
                    Log.e("trips error", error.toString());
                    Toast toast = Toast.makeText(this.context, "Error deleting trip", Toast.LENGTH_LONG);
                    toast.show();
                }
            );
            AppController.getInstance().addToRequestQueue(req, "string_req");
        }
        catch(Exception e){}
    }
}
