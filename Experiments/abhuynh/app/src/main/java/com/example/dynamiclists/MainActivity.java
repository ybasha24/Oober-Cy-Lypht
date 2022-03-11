package com.example.dynamiclists;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView1);

        ArrayList<String> list;
        list = new ArrayList<>(Arrays.asList("111,222,333,444,555,666".split(",")));
        listview.setAdapter(new CustomAdapter(list, getApplicationContext()) );
    }
}