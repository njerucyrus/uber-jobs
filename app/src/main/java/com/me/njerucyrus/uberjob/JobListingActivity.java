package com.me.njerucyrus.uberjob;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobListingActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private final String TAG = "JobListingActivity";
    Button btnShowJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnShowJobs = (Button) findViewById(R.id.btnShowJobs);
        btnShowJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jobsList();

            }
        });

    }
    public void jobsList(){
        db = FirebaseFirestore.getInstance();
        final Job jobObject = new Job();

        db.collection("jobs").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(JobListingActivity.this, "Found jobs.",
                                    Toast.LENGTH_LONG).show();
                            List<Object> jobs = new ArrayList<>();

                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Job  job = new Job();
                                job.setPostedAt((Date) document.get("postedAt"));
                                job.setPostedBy(document.get("postedBy").toString());
                                job.setTitle(document.get("title").toString());
                                job.setCategory(document.get("category").toString());
                                job.setDescription(document.get("description").toString());
                                job.setPrice((Double.parseDouble(document.get("price").toString())));
                                job.setNamedAddress(document.get("namedAddress").toString());
                                job.setLat(Double.parseDouble(document.get("lat").toString()));
                                job.setLng(Double.parseDouble(document.get("lng").toString()));
                                jobs.add(job);
                            }
                            jobObject.setJobs(jobs);
                            List<Job> testList = new ArrayList<>();

                            testList = jobObject.getJobs();
                            for (Job job : testList){
                                Log.d(TAG, "CATEGORY IS : "+job.getCategory());
                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(JobListingActivity.this, "No jobs found.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
