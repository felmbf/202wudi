package com.example.administrator.maininterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class pinglun extends AppCompatActivity {

    private RatingBar mRatingBar;
    private RatingBar nRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);

        mRatingBar = (RatingBar)findViewById(R.id.ratingbar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                /*Toast.makeText(pinglun.this, "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();*/
            }
        });
        nRatingBar = (RatingBar)findViewById(R.id.ratingbar1);
        nRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(pinglun.this, "rating"+String.valueOf(rating), Toast.LENGTH_SHORT).show();
                Toast.makeText(pinglun.this, "评论成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
