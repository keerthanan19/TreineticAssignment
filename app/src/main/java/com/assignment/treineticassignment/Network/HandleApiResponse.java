package com.assignment.treineticassignment.Network;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.assignment.treineticassignment.Database.DBUtils;
import com.assignment.treineticassignment.Object.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HandleApiResponse {
    private IpService mClient = null;
    List<Data> dataArrayList = new ArrayList<>();
    int count = 0;
    Context mContext;

    public HandleApiResponse(Context context, String url) {
        mContext = context;
        init(context,url);
    }

    private void init(Context context,String url) {
        mClient = new RetrofitClient().getClient(context,url).create(IpService.class);
    }

    public interface CallBackDataDelegate {
        void onResponseSuccess(List<Data> dataList);
        void onResponseSuccess(Data dataList);
        void onFailure(String error);
    }

    public void getAllData(CallBackDataDelegate delegate){
        mClient.getAllData().enqueue(new Callback<JsonArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.code() == 200 ) {
                    try {
                        JsonArray jsonArray = response.body();
                        List<Data> dataList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                            String id = jsonObject.get("id").getAsString();
                            String title = jsonObject.get("title").getAsString();
                            String price = jsonObject.get("price").getAsString();
                            double rating = jsonObject.get("rating").getAsDouble();
                            String description = jsonObject.get("description").getAsString();
                            String image = jsonObject.get("images").getAsString();

                            Data dataModel = new Data(id, title, price, rating, description, image);
                            DBUtils.insertData(dataModel,mContext);
                            dataList.add(dataModel);
                        }

                        delegate.onResponseSuccess(dataList);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("getAllData", "SOMETHING WENT WRONG");
                    try {
                        Log.e("getAllData", "response "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("getAllData", "onFailure: " + t);
                delegate.onFailure(t.toString());
            }
        });
    }


    public void getFeaturedProducts( CallBackDataDelegate delegate) {
        mClient.getFeaturedProducts().enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject jsonObject = response.body();
                        String id = jsonObject.get("id").getAsString();
                        String title = jsonObject.get("title").getAsString();
                        String price = jsonObject.get("price").getAsString();
                        double rating = jsonObject.get("rating").getAsDouble();
                        String description = jsonObject.get("description").getAsString();
                        String image = jsonObject.get("images").getAsString();

                        Data dataModel = new Data(id, title, price, rating, description, image);

                        delegate.onResponseSuccess(dataModel);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("getData", "SOMETHING WENT WRONG");
                    try {
                        Log.e("getData", "response " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("getData", "onFailure: " + t);
                delegate.onFailure(t.toString());
            }
        });
    }

}
