package com.example.bajeti;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {
    TextView t;
    TextView features;
    TextView developer;

    public About() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //About application texts
        t = findViewById(R.id.textprofile);
        t.setText("Summary\n\n"+"Money Manger application organizes your personal finance easily and effectively\n");
        features = findViewById(R.id.textFeatures);
        features.setText("Features\n\n"+"•\tAndroid Studio Support\n"
                +"•\tFriendly user interface & simple design\n"
                +"•\tAdd new incomes & expenses\n"
                +"•\tSave incomes & expenses\n"
                +"•\tCreate his/her own note\n"
                +"•\tAllow to select date and save data\n"
                +"•\tUpload images and save images\n"
                +"•\tExpenseActivity Tracker");
    }
}
