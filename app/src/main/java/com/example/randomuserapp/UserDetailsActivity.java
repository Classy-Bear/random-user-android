package com.example.randomuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //Previous Activity data
        Intent myIntent = getIntent();
        String firstName = myIntent.getStringExtra("firstName");
        String lastName = myIntent.getStringExtra("lastName");
        String cityLocation = myIntent.getStringExtra("cityLocation");
        String countryLocation = myIntent.getStringExtra("countryLocation");
        String email = myIntent.getStringExtra("email");
        String phone = myIntent.getStringExtra("phone");
        String pictureMedium = myIntent.getStringExtra("pictureMedium");
        String gender = myIntent.getStringExtra("gender");
        String cell = myIntent.getStringExtra("cell");
        String age = myIntent.getStringExtra("age");

        TextView firstNameTextView = findViewById(R.id.fullName);
        TextView cityLocationTextView = findViewById(R.id.city);
        TextView countryLocationTextView = findViewById(R.id.country);
        TextView emailTextView = findViewById(R.id.email);
        TextView phoneTextView = findViewById(R.id.phone);
        TextView genderTextView = findViewById(R.id.gender);
        TextView cellTextView = findViewById(R.id.cell);
        TextView greetingTextView = findViewById(R.id.greeting);
        ImageView pictureMediumImageView = findViewById(R.id.picture_thumbnail);

        firstNameTextView.setText(firstName);
        cityLocationTextView.setText(cityLocation);
        countryLocationTextView.setText(countryLocation);
        emailTextView.setText(email);
        phoneTextView.setText(phone);
        genderTextView.setText(gender);
        cellTextView.setText(cell);
        greetingTextView.setText(getString(R.string.greeting, firstName + " " + lastName, age));
        Picasso.get().load(pictureMedium).into(pictureMediumImageView);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();
        //El "Up Button" tendrá el mismo comportamiento
        // que el de "Back Button"
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
