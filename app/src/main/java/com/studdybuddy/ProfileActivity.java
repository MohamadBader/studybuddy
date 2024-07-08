package com.studdybuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends BaseActivity {

    private ImageView imageViewProfilePic;
    private TextView editTextName;
    private TextView editTextCourses;
    private TextView editTextMajor;
    private TextView editTextBio;
    private TextView textViewInterests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.profile, findViewById(R.id.content_frame));

        imageViewProfilePic = findViewById(R.id.imageViewProfilePic);
        editTextName = findViewById(R.id.editTextName);
        editTextCourses = findViewById(R.id.editTextCourses);
        editTextMajor = findViewById(R.id.editTextMajor);
        editTextBio = findViewById(R.id.editTextBio);
        textViewInterests = findViewById(R.id.textViewInterests);

        // Load user profile data
        loadUserProfile();
    }

    private void loadUserProfile() {
        // Example data, replace with actual user data
        imageViewProfilePic.setImageResource(R.drawable.john);
        editTextName.setText("John Doe");
        editTextCourses.setText("Data Structures, Introduction to Linguistic Theory");
        editTextMajor.setText("Computer Science");
        editTextBio.setText("I'm a computer science major and linguist.");
        textViewInterests.setText("Video games, Reading, Traveling");
    }

    @Override
    protected ArrayList<String> getMatchedUserNames() {
        // ProfileActivity does not need to provide matched user names
        return new ArrayList<>();
    }
}
