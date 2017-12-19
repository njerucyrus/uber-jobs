package com.me.njerucyrus.uberjob;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostJobActivity extends AppCompatActivity {

    private String mTitle;
    private String mCategory;
    private String mDesc;
    private Double mPrice;
    private FirebaseFirestore db;
    private final String TAG = "PostJobActivity";
    EditText txtTitle, txtCategory, txtDesc, txtPrice;
    Button btnPostJob;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_job);
        btnPostJob = (Button) findViewById(R.id.btnPostJob);
        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJob();
            }
        });

    }
    public void postJob(){
        txtTitle = (EditText)findViewById(R.id.txtTitle);
        mTitle = txtTitle.getText().toString();

        txtDesc = (EditText)findViewById(R.id.txtDescription);
        mDesc = txtDesc.getText().toString();

        txtCategory = (EditText)findViewById(R.id.txtCategory);
        mCategory = txtCategory.getText().toString();

        txtPrice = (EditText)findViewById(R.id.txtPrice);
        mPrice = Double.parseDouble(txtPrice.getText().toString());
        String uid = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

        Job job = new Job();
        job.setTitle(mTitle);
        job.setDescription(mDesc);
        job.setCategory(mCategory);
        job.setLat(-2.88999);
        job.setLng(43.55666);
        job.setNamedAddress("kahawa");
        job.setPostedBy(uid);
        job.setPrice(mPrice);
        job.setPostedAt(new Date());
        db = FirebaseFirestore.getInstance();

        db.collection("jobs")
                .add(job)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(PostJobActivity.this, "Job posted.",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(PostJobActivity.this, "Error occurred on posting job.",
                                Toast.LENGTH_LONG).show();
                    }
                });


    }
}