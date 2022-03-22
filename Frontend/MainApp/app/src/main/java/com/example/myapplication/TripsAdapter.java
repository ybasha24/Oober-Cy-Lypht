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

import com.example.myapplication.createride.SelectRideTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.trip_item, null);
        }

        TextView tv= view.findViewById(R.id.textView);
        try {
            JSONObject json = list.getJSONObject(position);
            Log.e("Json logging", json.toString());
            tv.setText("Place: " + json.getString("originAddress") + "->" + json.getString("destAddress") +
                    "\nTime: " + json.getString("scheduledStartDate") + "->" + json.getString("scheduledEndDate"));
        }
        catch(Exception e){}

        Button editTripButton = view.findViewById(R.id.editTripButton);
        Button deleteTripButton = view.findViewById(R.id.deleteTripButton);

        editTripButton.setOnClickListener(v -> {
            Intent i = new Intent(context, SelectRideTime.class);
            try {
                i.putExtra("editing", true);
                i.putExtra("tripId", list.getJSONObject(position).getInt("id"));
            } catch(JSONException e) {}
        });
        deleteTripButton.setOnClickListener(v -> {

        });

        return view;
    }
}