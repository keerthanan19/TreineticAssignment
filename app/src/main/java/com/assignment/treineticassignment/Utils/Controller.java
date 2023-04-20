package com.assignment.treineticassignment.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.treineticassignment.MainActivity;
import com.assignment.treineticassignment.Network.HandleApiResponse;
import com.assignment.treineticassignment.Object.Data;
import com.assignment.treineticassignment.ProductDetails;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static int progressStatus;

    public static void getAllData(Activity activity){
        HandleApiResponse handleApiResponse = new HandleApiResponse(activity,"https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/");
        handleApiResponse.getAllData( new HandleApiResponse.CallBackDataDelegate() {
            @Override
            public void onResponseSuccess(List<Data> dataList) {
                Log.e("getAllChild", "result "+dataList.size());
                int count = 0;
                activity.startActivity(new Intent(activity, MainActivity.class));
            }

            @Override
            public void onResponseSuccess(Data dataList) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    public static void getFeaturedProducts(Activity activity){
        HandleApiResponse handleApiResponse = new HandleApiResponse(activity,"https://59b8726e92ccc3eb44b0c193eeef96f6.m.pipedream.net/");
        handleApiResponse.getFeaturedProducts(new HandleApiResponse.CallBackDataDelegate() {
            @Override
            public void onResponseSuccess(List<Data> dataList) {
                // Do something with the data list in the activity

            }

            @Override
            public void onResponseSuccess(Data data) {
                Intent intent = new Intent(activity, ProductDetails.class);
                intent.putExtra("data", data);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                // Handle the failure case
            }
        });
    }

    public static void progressBar(int status, Handler handler, ProgressBar progressBar, TextView textView) {
        progressStatus = status ;
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 20;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
