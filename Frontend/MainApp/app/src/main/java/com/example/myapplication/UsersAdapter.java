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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.app.AppController;
import com.example.myapplication.createride.SelectRideTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsersAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;

    public UsersAdapter(JSONArray list, Context context) {
        for(int i = 0; i < list.length(); i++){
            try {
                JSONObject user = list.getJSONObject(i);
                try {
                    if (user.getBoolean("anAdmin") == true) {
                        list.remove(i);
                    }
                } catch(Exception e1) {}
            } catch(Exception e2) {}
        }
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
            view = inflater.inflate(R.layout.user_item, null);
        }

        TextView tv= view.findViewById(R.id.textView);
        try {
            JSONObject json = list.getJSONObject(position);
            Log.e("Json logging", json.toString());
            tv.setText("ID: " + json.getString("id") + ": " + json.getString("firstName") + " " + json.getString("lastName"));
        }
        catch(Exception e){}

        Button editUserButton = view.findViewById(R.id.editUserButton);

        editUserButton.setOnClickListener(v -> {
            try {
                MainActivity.accountObj = list.getJSONObject(position);
                Intent i = new Intent(this.context, ProfileSettings.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.context.startActivity(i);
            } catch(Exception e) {}
        });

        Button deleteUserButton = view.findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(v -> {
            try {
                JSONObject json = list.getJSONObject(position);
                int id = json.getInt("id");
                String url = "http://coms-309-030.class.las.iastate.edu:8080/user/deleteUser" + "?id=" + id;
                StringRequest req = new StringRequest(Request.Method.DELETE, url,
                        response -> Log.e("Delete user", response),
                        error -> Toast.makeText(context, "Error: " + error, Toast.LENGTH_LONG));
                AppController.getInstance().addToRequestQueue(req, "post_object_tag");
            } catch(Exception e){}
        });

        return view;
    }
}
