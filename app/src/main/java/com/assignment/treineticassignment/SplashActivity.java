package com.assignment.treineticassignment;

import static com.assignment.treineticassignment.Utils.Controller.getAllData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.treineticassignment.Database.DBUtils;
import com.assignment.treineticassignment.Utils.Controller;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    Context mContext;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = SplashActivity.this;
        DBUtils.deleteAllData(mContext);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        TextView textView = (TextView) findViewById(R.id.textView);
        // Start long running operation in a background thread
        int progressStatus = 0;
        Controller.progressBar(progressStatus,handler, progressBar, textView);
        getAllData(this);
    }
}