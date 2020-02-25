package com.example.randomuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //Previous Activity data
        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("name");
        String username = myIntent.getStringExtra("username");
        String email = myIntent.getStringExtra("email");
        String phone = myIntent.getStringExtra("phone");
        String website = myIntent.getStringExtra("website");
        String street = myIntent.getStringExtra("street");
        String suite = myIntent.getStringExtra("suite");
        String city = myIntent.getStringExtra("city");
        String zipCode = myIntent.getStringExtra("zipCode");
        String companyName = myIntent.getStringExtra("companyName");
        String catchPhrase = myIntent.getStringExtra("catchPhrase");

        TextView nameTextView = findViewById(R.id.name_output);
        TextView fullNameTextView = findViewById(R.id.fullName);
        TextView userNameTextView = findViewById(R.id.username_output);
        TextView emailTextView = findViewById(R.id.email_output);
        TextView phoneTextView = findViewById(R.id.phone_output);
        TextView websiteTextView = findViewById(R.id.website_output);
        TextView streetTextView = findViewById(R.id.street_output);
        TextView suiteTextView = findViewById(R.id.suite_output);
        TextView cityTextView = findViewById(R.id.city_output);
        TextView zipCodeTextView = findViewById(R.id.zipcode_output);
        TextView companyNameTextView = findViewById(R.id.companyName_output);
        TextView catchPhraseTextView = findViewById(R.id.catchPhrase_output);
        TextView greetingTextView = findViewById(R.id.greeting);

        nameTextView.setText(name);
        fullNameTextView.setText(name);
        userNameTextView.setText(username);
        emailTextView.setText(email);
        phoneTextView.setText(phone);
        websiteTextView.setText(website);
        streetTextView.setText(street);
        suiteTextView.setText(suite);
        cityTextView.setText(city);
        zipCodeTextView.setText(zipCode);
        companyNameTextView.setText(companyName);
        catchPhraseTextView.setText(catchPhrase);
        greetingTextView.setText(getString(R.string.greeting, name,companyName));
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
