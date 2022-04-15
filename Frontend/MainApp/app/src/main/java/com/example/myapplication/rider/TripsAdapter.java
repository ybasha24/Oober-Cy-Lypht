package com.example.myapplication.rider;

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
import com.example.myapplication.driver.TripsList;
import com.example.myapplication.endpoints.Endpoints;
import com.example.myapplication.rider.searchtrip.SearchPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * adapter class that shows all the trips of a rider
 */
public class TripsAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;

    /**
     * creates a TripsAdapter object
     *
     * @param list    list of trips
     * @param context context to put the list on
     */
    public TripsAdapter(JSONArray list, Context context) {
        if (list == null) {
            this.list = null;
        } else {
            this.list = list;
        }
        this.context = context;
    }

    /**
     * get size of list
     *
     * @return size of list
     */
    @Override
    public int getCount() {
        return list.length();
    }

    /**
     * gets Object in list at specificed position
     *
     * @param pos position of object
     * @return object as position pos
     */
    @Override
    public Object getItem(int pos) {
        try {
            return list.get(pos);
        } catch (Exception e) {
            return new Object();
        }
    }

    /**
     * returns 0
     *
     * @param pos unused variable
     * @return 0
     */
    @Override
    public long getItemId(int pos) {
        return 0;
    }

    /**
     * describes how the list elements are displayed
     *
     * @param position    position in list
     * @param convertView convertView object
     * @param parent      parent for this view
     * @return the created View object
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (list != null) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.trip_item2, null);
            }

            TextView tv = view.findViewById(R.id.riderSearchText);
            try {
                JSONObject json = list.getJSONObject(position);
                Log.e("Json logging", json.toString());
                tv.setText("From: " + json.getString("originAddress") + "\nTo: " + json.getString("destAddress") +
                        "\nTime: " + json.getString("scheduledStartDate") + "\n->" + json.getString("scheduledEndDate"));
            } catch (Exception e) {
            }

            Button addToTripButton = view.findViewById(R.id.add_trip);
            addToTripButton.setOnClickListener(v -> {
                addToTrip(position);
            });
            return view;
        }
        return null;
    }

    /**
     * Adds a rider to a trip based off requirements radius and location requirements
     * @param position
     */
    public void addToTrip(int position) {
       try{
           String url = Endpoints.AddRiderToTripUrl+list.getJSONObject(position).getInt("id")+
                   "&riderId="+MainActivity.accountObj.getInt("id");
           StringRequest req = new StringRequest(Request.Method.PUT, url,
                   response -> {
                       Intent i = new Intent(this.context, HomePage.class);
                       i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       this.context.startActivity(i);
                       Toast toast = Toast.makeText(this.context, "Successfully added to trip", Toast.LENGTH_LONG);
                       toast.show();
                   },
                   error -> {
                       Log.e("trips error", error.toString());
                       Toast toast = Toast.makeText(this.context, "Error adding trip", Toast.LENGTH_LONG);
                       toast.show();
                   }
           );
           AppController.getInstance().addToRequestQueue(req, "string_req");
       }
       catch (Exception e){}
    }

}
