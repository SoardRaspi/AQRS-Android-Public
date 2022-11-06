package com.example.aqrs;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> latitude;
    private final ArrayList<String> longitude;
    private final ArrayList<String> time;

    MainActivity mainActivity;

    public ListAdapter(Activity context, ArrayList<String> latitude, ArrayList<String> longitude, ArrayList<String> time) {
        super(context, R.layout.alert_list, latitude);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.latitude=latitude;
        this.longitude=longitude;
        this.time = time;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.alert_list, null,true);

        TextView latitude_tv = (TextView) rowView.findViewById(R.id.latitude);
        TextView longitude_tv = (TextView) rowView.findViewById(R.id.longitude);
        Button locate = (Button) rowView.findViewById(R.id.locate);
        TextView time_tv = (TextView) rowView.findViewById(R.id.time);

        latitude_tv.setText("Latitude: " + latitude.get(position));
        longitude_tv.setText("Longitude: " + longitude.get(position));
        time_tv.setText("Time issued: " + time.get(position));

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rowView;
    };
}