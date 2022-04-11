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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * adapter class that shows all the trips ever made
 */
public class ChatAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list;
    private Context context;
    private Button chatButton;
    private TextView tv;

    /**
     * creates a ChatAdapter object
     * @param list list of users to chat with
     * @param context context to put the list on
     */
    public ChatAdapter(JSONArray list, Context context) {
        this.list = list;
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

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chat_user_item, null);
            chatButton = view.findViewById(R.id.editTripButton);
            tv = view.findViewById(R.id.textView);
        }

        try {
            JSONObject json = list.getJSONObject(position);
            Log.d("json", json.toString());
            tv.setText(json.getString("firstName") + " " + json.getString("lastName"));
        }
        catch(Exception e){
            Log.e("error", e.toString());
        }

        chatButton.setOnClickListener(v -> chat(position));

        return view;
    }

    /**
     * edits a trip
     * @param position position of the trip in the list
     */
    public void chat(int position){
        Intent i = new Intent(this.context, ChatChooseUser.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(i);

//        try {
//            Log.e("trips error", Endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"));
//
//            StringRequest req = new StringRequest(Request.Method.DELETE, Endpoints.DeleteTripUrl + "?id=" + list.getJSONObject(position).getInt("id"),
//                    response -> {
//                        Toast toast = Toast.makeText(this.context, "Successfully deleted trip", Toast.LENGTH_LONG);
//                        toast.show();
//                        Log.d("success", response);
//                        Intent i = new Intent(this.context, TripsList.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        this.context.startActivity(i);
//                    },
//                    error -> {
//                        Log.e("error", error.toString());
//                        Toast toast = Toast.makeText(this.context, "Error deleting trip", Toast.LENGTH_LONG);
//                        toast.show();
//                    }
//            );
//            AppController.getInstance().addToRequestQueue(req, "string_req");
//        }
//        catch(Exception e){
//            Log.e("error", e.toString());
//        }
    }


}
