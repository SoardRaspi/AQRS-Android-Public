package com.example.aqrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView alerts;

    private FirebaseFirestore db;

    ArrayList<String> latitudes = new ArrayList<String>();
    ArrayList<String> longitudes = new ArrayList<String>();
    ArrayList<String> times = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        alerts = (ListView) findViewById(R.id.alertList);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels / 2;
        int width = displayMetrics.widthPixels;

//        mapView.getLayoutParams().height = height;
//        mapView.getLayoutParams().width = width;

        db.collection("triggers/27122020/27122020")
                .whereEqualTo("data", "trigger")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Toast.makeText(MainActivity.this, Objects.requireNonNull(document.getData().get("lat")).toString(), Toast.LENGTH_LONG).show();
                                latitudes.add(String.valueOf(document.getData().get("lat")));
                                longitudes.add(String.valueOf(document.getData().get("long")));
                                times.add(String.valueOf(document.getData().get("time")));

                                try {
                                    ListAdapter adapter = new ListAdapter(MainActivity.this, latitudes, longitudes, times);
                                    alerts.setAdapter(adapter);
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "Some error occurred.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error getting alerts: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}