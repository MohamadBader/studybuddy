package com.studdybuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends BaseActivity {

    private TextView name;
    private ListView matchListView;
    private TextView noMatchesTextView;
    private TextView titleMatches;
    private TextView descriptionMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.matches_activity, findViewById(R.id.content_frame));

        titleMatches = findViewById(R.id.titleMatches);
        descriptionMatches = findViewById(R.id.descriptionMatches);
        name = findViewById(R.id.name);
        matchListView = findViewById(R.id.matchListView);
        noMatchesTextView = findViewById(R.id.noMatchesTextView);

        List<HomeActivity.User> matchedUsers = SharedData.getInstance().getMatchedUsers();
        if (matchedUsers != null && !matchedUsers.isEmpty()) {
            noMatchesTextView.setVisibility(View.GONE);
            matchListView.setVisibility(View.VISIBLE);

            ArrayList<String> matchedUserNames = new ArrayList<>();
            name.setVisibility(View.VISIBLE);
            for (HomeActivity.User user : matchedUsers) {
                matchedUserNames.add(user.getFirstName() + " " + user.getLastName());
            }

            ArrayAdapter<String> matchListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchedUserNames);
            matchListView.setAdapter(matchListAdapter);
        } else {
            name.setVisibility(View.VISIBLE);
            matchListView.setVisibility(View.GONE);
            noMatchesTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected ArrayList<String> getMatchedUserNames() {
        return new ArrayList<>();
    }
}
