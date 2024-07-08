package com.studdybuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends BaseActivity {

    private ListView chatListView;
    private TextView noChatsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_chats, findViewById(R.id.content_frame));

        chatListView = findViewById(R.id.chatListView);
        noChatsTextView = findViewById(R.id.noChatsTextView);

        List<HomeActivity.User> chatUsers = SharedData.getInstance().getChatUsers();
        if (chatUsers != null && !chatUsers.isEmpty()) {
            noChatsTextView.setVisibility(View.GONE);
            chatListView.setVisibility(View.VISIBLE);
            ChatAdapter chatAdapter = new ChatAdapter(this, chatUsers);
            chatListView.setAdapter(chatAdapter);
        } else {
            chatListView.setVisibility(View.GONE);
            noChatsTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected ArrayList<String> getMatchedUserNames() {
        // ChatsActivity does not need to provide matched user names
        return new ArrayList<>();
    }
}
