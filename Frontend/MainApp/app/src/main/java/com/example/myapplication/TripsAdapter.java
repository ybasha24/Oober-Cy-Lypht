package com.example.myapplication;

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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.app.AppController;
import com.example.myapplication.createride.SelectRideTime;
import com.example.myapplication.driver.DriverCreatedRides;
import com.example.myapplication.driver.DriverHomePage;
import com.example.myapplication.endpoints.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class TripsAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;

    public TripsAdapter(JSONArray list, Context context) {
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
        Intent i = new Intent(this.context, SelectRideTime.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            i.putExtra("editing", true);
            i.putExtra("tripId", list.getJSONObject(position).getInt("id"));
        }
        catch(JSONException e) {}
        this.context.startActivity(i);
    }

    public void deleteTrip(int position){
        try {
            StringRequest req = new StringRequest(Request.Method.DELETE, endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"),
                response -> {
                    Intent i = new Intent(this.context, DriverCreatedRides.class);
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
