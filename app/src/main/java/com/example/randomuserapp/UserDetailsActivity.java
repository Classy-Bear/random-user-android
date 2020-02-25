package com.example.randomuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.randomuserapp.POJO.User;

import java.util.ArrayList;

public class UserDetailsActivity extends AppCompatActivity {

    private int[] textViews = {
            R.id.name_output,
            R.id.fullName,
            R.id.username_output,
            R.id.email_output,
            R.id.phone_output,
            R.id.website_output,
            R.id.street_output,
            R.id.suite_output,
            R.id.city_output,
            R.id.zipcode_output,
            R.id.companyName_output,
            R.id.catchPhrase_output,
            R.id.greeting
    };

    public ArrayList<String> userData(User user) {
        ArrayList<String> userList = new ArrayList<>();
        userList.add(user.getName());
        userList.add(user.getName());
        userList.add(user.getUsername());
        userList.add(user.getEmail());
        userList.add(user.getPhone());
        userList.add(user.getWebsite());
        userList.add(user.getStreet());
        userList.add(user.getSuite());
        userList.add(user.getCity());
        userList.add(user.getZipCode());
        userList.add(user.getCompanyName());
        userList.add(user.getCatchPhrase());
        userList.add(getString(R.string.greeting,user.getName(),user.getCompanyName()));
        return userList;
    }

    public void setTextViews(ArrayList<String> userData, int[] textViewResources){
        for (int i = 0; i < textViewResources.length; i++) {
            TextView textView = findViewById(textViewResources[i]);
            textView.setText(userData.get(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        //Previous Activity data
        Intent myIntent = getIntent();
        User user = (User) myIntent.getSerializableExtra("user");
        assert user != null;
        setTextViews(userData(user), textViews);
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
