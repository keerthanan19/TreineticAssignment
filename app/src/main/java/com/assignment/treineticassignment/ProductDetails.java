package com.assignment.treineticassignment;

import static com.assignment.treineticassignment.Utils.Controller.getAllData;
import static com.assignment.treineticassignment.Utils.Controller.getFeaturedProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.assignment.treineticassignment.Adapter.CustomViewPagerAdapter;
import com.assignment.treineticassignment.Object.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProductDetails extends AppCompatActivity {

    List<String> imageUrls = new ArrayList<>();
    private ViewPager viewPager;
    private ImageView[] dots;
    private int currentPage = 0;
    private Timer timer;

    TextView tittle,rating_text,price,descriptiom;
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Data data = (Data) getIntent().getSerializableExtra("data");


        for(int i= 0 ;i<3;i++){
            imageUrls.add(data.getImages());
        }

        viewPager = findViewById(R.id.viewPager);
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        addDots();

        // Auto start the slide show
        autoStartSlideShow();

        ImageButton menuButton = findViewById(R.id.back_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton shoppingButton = findViewById(R.id.shopping_cart_button);
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle menu item press
            }
        });

        tittle = findViewById(R.id.tittle);
        rating_text = findViewById(R.id.rating_text);
        price = findViewById(R.id.price);
        descriptiom = findViewById(R.id.descriptiom);

        tittle.setText(data.getTitle());
        rating_text.setText( String.valueOf(data.getRating()));
        price.setText(data.getPrice());
        descriptiom.setText(data.getDescription());

        rating = findViewById(R.id.rating);
        rating.setRating(Float.parseFloat(String.valueOf(data.getRating())) );

    }

    private void addDots() {
        ViewGroup dotsLayout = findViewById(R.id.dotsLayout);
        dots = new ImageView[imageUrls.size()];

        for (int i = 0; i < imageUrls.size(); i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.dot);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    viewPager.setCurrentItem(position);
                }
            });
            dots[i].setTag(i);
            dotsLayout.addView(dots[i]);
        }

        dots[currentPage].setImageResource(R.drawable.selected_dot);
    }

    private void autoStartSlideShow() {
        final int DELAY_MS = 5000;
        final int PERIOD_MS = 5000;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == imageUrls.size()) {
                            currentPage = 0;
                        }

                        viewPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}