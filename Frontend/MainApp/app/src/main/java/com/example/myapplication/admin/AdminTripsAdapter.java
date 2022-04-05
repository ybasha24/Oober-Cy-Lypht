package com.example.myapplication.admin;

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
import com.example.myapplication.R;
import com.example.myapplication.admin.TripsList;
import com.example.myapplication.app.AppController;
import com.example.myapplication.createride.SelectRideTime;
<<<<<<< HEAD:Frontend/MainApp/app/src/main/java/com/example/myapplication/admin/AdminTripsAdapter.java
import com.example.myapplication.endpoints.endpoints;
=======
import com.example.myapplication.driver.DriverCreatedRides;
import com.example.myapplication.driver.DriverHomePage;
import com.example.myapplication.endpoints.*;
>>>>>>> fe-82:Frontend/MainApp/app/src/main/java/com/example/myapplication/TripsAdapter.java

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminTripsAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;
    Button editTripButton;
    Button deleteTripButton;
    TextView tv;

    public AdminTripsAdapter(JSONArray list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.length();
    }

    @Override
    public Object getItem(int pos) {
        try {
            return list.get(pos);
        }
        catch(Exception e){ return new Object(); }
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
<<<<<<< HEAD:Frontend/MainApp/app/src/main/java/com/example/myapplication/admin/AdminTripsAdapter.java
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.admin_trip_item, null);
            editTripButton = view.findViewById(R.id.editTripButton);
            deleteTripButton = view.findViewById(R.id.deleteTripButton);
            tv = view.findViewById(R.id.textView);
        }

        try {
            JSONObject json = list.getJSONObject(position);
            Log.d("json", json.toString());
            tv.setText("From: " + json.getString("originAddress") + "\nTo: " + json.getString("destAddress") +
                    "\nTime: " + json.getString("scheduledStartDate") + "\n->" + json.getString("scheduledEndDate"));
        }
        catch(Exception e){
            Log.e("error", e.toString());
        }

        editTripButton.setOnClickListener(v -> editTrip(position));
        deleteTripButton.setOnClickListener(v -> deleteTrip(position));
=======
        if (!otherConstants.SearchTrip) {
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
>>>>>>> fe-82:Frontend/MainApp/app/src/main/java/com/example/myapplication/TripsAdapter.java

            deleteTripButton.setOnClickListener(v -> deleteTrip(position));

            return view;
        }
        else{
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
        }
        return view;
    }


    public void editTrip(int position){
        try {
            Intent i = new Intent(this.context, SelectRideTime.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("editing", true);
            i.putExtra("tripId", list.getJSONObject(position).getInt("id"));
            this.context.startActivity(i);
        }
        catch(JSONException e) {
            Log.e("error", e.toString());
        }
    }

    public void deleteTrip(int position){
        try {
            Log.e("trips error", endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"));

            StringRequest req = new StringRequest(Request.Method.DELETE, endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"),
                response -> {
                    Toast toast = Toast.makeText(this.context, "Successfully deleted trip", Toast.LENGTH_LONG);
                    toast.show();
                    Log.d("success", response);
                    Intent i = new Intent(this.context, TripsList.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.context.startActivity(i);
                },
                error -> {
                    Log.e("error", error.toString());
                    Toast toast = Toast.makeText(this.context, "Error deleting trip", Toast.LENGTH_LONG);
                    toast.show();
                }
            );
            AppController.getInstance().addToRequestQueue(req, "string_req");
        }
        catch(Exception e){
            Log.e("error", e.toString());
        }
    }
}
