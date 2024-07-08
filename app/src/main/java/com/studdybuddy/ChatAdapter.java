package com.studdybuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<HomeActivity.User> {

    private Context mContext;
    private List<HomeActivity.User> userList;

    public ChatAdapter(Context context, List<HomeActivity.User> list) {
        super(context, 0 , list);
        mContext = context;
        userList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.chat_row, parent, false);

        HomeActivity.User currentUser = userList.get(position);

        ImageView chatPhoto = listItem.findViewById(R.id.chatPhoto);
        chatPhoto.setImageResource(currentUser.getImageResId());

        TextView chatName = listItem.findViewById(R.id.chatName);
        chatName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        TextView chatLastMessage = listItem.findViewById(R.id.chatLastMessage);
        chatLastMessage.setText("You Matched❤! Say Hi"); // Placeholder for the last message

        return listItem;
    }
}
