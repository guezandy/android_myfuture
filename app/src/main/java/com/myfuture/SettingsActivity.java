package com.myfuture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.myfuture.view.*;
import com.parse.ParseUser;
import com.myfuture.R;
/**
 * Activity that displays the settings screen.
 */
public class SettingsActivity extends Activity {

    private RadioGroup searchDistanceGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settngs);

        // The search distance choices
        searchDistanceGroup = (RadioGroup) findViewById(R.id.searchDistanceGroup);
        float searchDistance = MyFutureApplication.getSearchDistance();
        if (searchDistance == 4000) {
            searchDistanceGroup.check(R.id.feet4000Button);
        } else if (searchDistance == 1000) {
            searchDistanceGroup.check(R.id.feet1000Button);
        } else {
            searchDistanceGroup.check(R.id.feet250Button);
        }
        // Set up the selection handler to save the selection to the application
        searchDistanceGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.feet4000Button:
                        MyFutureApplication.setSearchDistance(4000);
                        break;
                    case R.id.feet1000Button:
                        MyFutureApplication.setSearchDistance(100);
                        break;
                    case R.id.feet250Button:
                        MyFutureApplication.setSearchDistance(250);
                        break;
                }
            }
        });

        // Set up the save button click handler
        ((Button) findViewById(R.id.saveDistance)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start and intent for the dispatch activity
                Intent intent = new Intent(SettingsActivity.this, com.myfuture.view.MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}