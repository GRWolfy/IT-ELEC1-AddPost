package com.example.it_elec1_addpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentsAdapter extends ArrayAdapter<Comments> {
    public CommentsAdapter(Context context, ArrayList<Comments> arrComments) {
        super(context, 0, arrComments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup child) {
        Comments currentComments = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, child, false);
        }

        TextView txtComment = convertView.findViewById(R.id.txtComment);

        txtComment.setText(currentComments.getComment());

        return convertView;
    }
}