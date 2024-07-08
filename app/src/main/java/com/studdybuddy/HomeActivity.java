package com.studdybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends BaseActivity {

    private TextView suggestionNameTextView;
    private ImageView infoButtonImageView;
    private ImageView suggestionProgramImageView;
    private ImageView suggestionYearImageView;
    private ImageView suggestionPhotoImageView;
    private TextView editTextCourses;
    private TextView editTextMajor;
    private TextView editTextBio;

    // Map of users and user list
    private Map<String, User> users;
    private List<User> userList;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.suggestion_card_view, findViewById(R.id.content_frame));

        infoButtonImageView = findViewById(R.id.infoButtonImageView2);
        suggestionNameTextView = findViewById(R.id.suggestionNameTextView);
        suggestionProgramImageView = findViewById(R.id.suggestionProgramImageView);
        suggestionYearImageView = findViewById(R.id.suggestionYearImageView);
        suggestionPhotoImageView = findViewById(R.id.suggestionPhotoImageView);
        editTextCourses = findViewById(R.id.editTextCourses);
        editTextMajor = findViewById(R.id.editTextMajorCard);
        editTextBio = findViewById(R.id.editTextBio);

        // Initialize users with image resource IDs
        users = new HashMap<>();
        users.put("johndoe@example.com", new User("7", "johndoe@example.com", "password123", "John", "Doe", "Computer Science", "Sophomore", Arrays.asList("Data Structures", "Algorithms", "Operating Systems"), 3.64, "I'm a computer science major and linguist.", R.drawable.bash));
        users.put("abdulrahman@example.com", new User("2", "abdulrahman@example.com", "password123", "Abdulrahman", "Yunis", "Mathematics", "Junior", Arrays.asList("Calculus", "Linear Algebra", "Statistics"), 3.75, "I'm a mathematics major with a passion for statistics.", R.drawable.abdulrahman));
        users.put("muhammad@example.com", new User("3", "muhammad@example.com", "password123", "Muhammad", "Bader", "Biology", "Senior", Arrays.asList("Genetics", "Microbiology", "Ecology"), 3.80, "I'm a biology major interested in genetics.", R.drawable.bader));
        users.put("anas@example.com", new User("4", "anas@example.com", "password123", "Anas", "Kali", "Physics", "Freshman", Arrays.asList("Mechanics", "Electromagnetism", "Quantum Physics"), 3.90, "I'm a physics major fascinated by quantum mechanics.", R.drawable.anas));
        users.put("osama@example.com", new User("5", "osama@example.com", "password123", "Osama", "Abdo", "Software Engineering", "Junior", Arrays.asList("Software Design", "Databases", "Web Development"), 3.85, "I'm a software engineering major with a focus on web development.", R.drawable.osama));
        users.put("jamal@example.com", new User("6", "jamal@example.com", "password123", "Jamal", "Fattal", "Chemistry", "Senior", Arrays.asList("Organic Chemistry", "Inorganic Chemistry", "Biochemistry"), 3.95, "I'm a chemistry major interested in biochemistry.", R.drawable.jamal));
        users.put("johndoe@example.com", new User("1", "johndoe@example.com", "password123", "Irbaad", "Bashir", "Computer Science", "Sophomore", Arrays.asList("Data Structures", "Algorithms", "Operating Systems"), 3.64, "I'm a computer science major and linguist.", R.drawable.bash));
        users.put("abdulrahman@example.com", new User("2", "abdulrahman@example.com", "password123", "Abdulrahman", "Yunis", "Mathematics", "Junior", Arrays.asList("Calculus", "Linear Algebra", "Statistics"), 3.75, "I'm a mathematics major with a passion for statistics.", R.drawable.abdulrahman));
        users.put("muhammad@example.com", new User("3", "muhammad@example.com", "password123", "Muhammad", "Bader", "Biology", "Senior", Arrays.asList("Genetics", "Microbiology", "Ecology"), 3.80, "I'm a biology major interested in genetics.", R.drawable.bader));
        users.put("david@example.com", new User("1", "david@example.com", "password123", "David", "Hill", "English Literature", "Sophomore", Arrays.asList("British Literature", "American Literature", "World Literature"), 3.70, "I'm an English literature major with a love for world literature.", R.drawable.raid));
        // Convert map to list
        userList = new ArrayList<>(users.values());
        // Populate the views with the first user's data
        if (!userList.isEmpty()) {
            populateViews(userList.get(currentIndex));
        } else {
            Toast.makeText(HomeActivity.this, "User list is empty", Toast.LENGTH_SHORT).show();
        }

        // Set listeners for the tick and cross images
        suggestionProgramImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchWithUser();
            }
        });

        suggestionYearImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipUser();
            }
        });
    }

    private void populateViews(User user) {
        if (user != null) {
            suggestionNameTextView.setText(user.getFirstName() + " " + user.getLastName());
            editTextCourses.setText(String.join(", ", user.getCourses()));
            editTextMajor.setText(user.getMajor());
            editTextBio.setText(user.getBio());

            // Set the user's image
            suggestionPhotoImageView.setImageResource(user.getImageResId());

            // Set user's major and class level as the photo's content description for demonstration purposes
            suggestionPhotoImageView.setContentDescription(user.getMajor() + " - " + user.getClassLevel());
        } else {
            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void matchWithUser() {
        if (currentIndex < userList.size()) {
            User matchedUser = userList.get(currentIndex);
            SharedData.getInstance().addMatchedUser(matchedUser);
            SharedData.getInstance().addChatUser(matchedUser);

            Toast.makeText(HomeActivity.this, "You matched with " + suggestionNameTextView.getText(), Toast.LENGTH_SHORT).show();
            showNextUser();
        } else {
            Toast.makeText(this, "No more users to match", Toast.LENGTH_SHORT).show();
        }
    }

    private void skipUser() {
        if (currentIndex < userList.size()) {
            Toast.makeText(HomeActivity.this, "You skipped " + suggestionNameTextView.getText(), Toast.LENGTH_SHORT).show();
            showNextUser();
        } else {
            Toast.makeText(this, "No more users to skip", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNextUser() {
        currentIndex++;
        if (currentIndex < userList.size()) {
            populateViews(userList.get(currentIndex));
        } else {
            Toast.makeText(HomeActivity.this, "No more users", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void handleNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_profile:
                Intent profileIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_match:
                Intent matchesIntent = new Intent(this, MatchesActivity.class);
                startActivity(matchesIntent);
                break;
            case R.id.nav_chatslist:
                Intent chatsIntent = new Intent(this, ChatsActivity.class);
                startActivity(chatsIntent);
                break;
            // Add more cases as needed
        }
    }

    @Override
    protected ArrayList<String> getMatchedUserNames() {
        ArrayList<String> matchedUserNames = new ArrayList<>();
        for (User user : SharedData.getInstance().getMatchedUsers()) {
            matchedUserNames.add(user.getFirstName() + " " + user.getLastName());
        }
        return matchedUserNames;
    }

    // User class to hold dummy data
    public static class User {
        private String id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String major;
        private String classLevel;
        private List<String> courses;
        private double gpa;
        private String bio;
        private int imageResId;  // Drawable resource ID for the user's image

        public User(String id, String username, String password, String firstName, String lastName, String major, String classLevel, List<String> courses, double gpa, String bio, int imageResId) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.major = major;
            this.classLevel = classLevel;
            this.courses = courses;
            this.gpa = gpa;
            this.bio = bio;
            this.imageResId = imageResId;
        }

        // Getters and setters

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getClassLevel() {
            return classLevel;
        }

        public void setClassLevel(String classLevel) {
            this.classLevel = classLevel;
        }

        public List<String> getCourses() {
            return courses;
        }

        public void setCourses(List<String> courses) {
            this.courses = courses;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public int getImageResId() {
            return imageResId;
        }

        public void setImageResId(int imageResId) {
            this.imageResId = imageResId;
        }
    }
}
