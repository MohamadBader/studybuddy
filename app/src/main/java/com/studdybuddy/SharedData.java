package com.studdybuddy;

import java.util.ArrayList;
import java.util.List;

public class SharedData {
    private static SharedData instance;
    private List<HomeActivity.User> matchedUsers;
    private List<HomeActivity.User> chatUsers;

    private SharedData() {
        matchedUsers = new ArrayList<>();
        chatUsers = new ArrayList<>();
    }

    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public List<HomeActivity.User> getMatchedUsers() {
        return matchedUsers;
    }

    public void addMatchedUser(HomeActivity.User user) {
        matchedUsers.add(user);
    }

    public List<HomeActivity.User> getChatUsers() {
        return chatUsers;
    }

    public void addChatUser(HomeActivity.User user) {
        chatUsers.add(user);
    }
}
