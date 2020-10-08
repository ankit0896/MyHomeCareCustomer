package com.example.myhomecare.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhomecare.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class RatingReviewsActivity extends AppCompatActivity {

    CheckBox box;
    RecyclerView rating_list;
    TextView ratingReviewNumber;
    ExtendedFloatingActionButton reviewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_reviews);
        initView();


    }

    private void initView() {
        box = findViewById(R.id.cb_rating_review_withphoto);
        rating_list = findViewById(R.id.rv_rating_reviews_list);
//        reviewButton  = findViewById(R.id.extended_fab_review_button);
//        ratingReviewNumber = findViewById(R.id.tv_rating_reviews_number);


        loadItems();
        clickEvents();
    }

    private void loadItems() {
    }

    private void clickEvents() {
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                }else{

                }
            }
        });
        
//        reviewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(RatingReviewsActivity.this, "Write a reviews", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}