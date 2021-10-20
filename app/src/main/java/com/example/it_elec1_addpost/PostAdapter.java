package com.example.it_elec1_addpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> arrPost) {
        super(context, 0, arrPost);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post currentPost = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item,parent, false );
        }

        TextView txtOutput_Name = convertView.findViewById(R.id.txtOutput_Name);
        TextView txtOutput_Caption = convertView.findViewById(R.id.txtOutput_Caption);
        TextView txtFirstLetter = convertView.findViewById(R.id.txtFirstLetter);
        TextView txtDateTIme = convertView.findViewById(R.id.txtDateTime);

        txtDateTIme.setText(currentPost.getDateTime());
        txtOutput_Caption.setText(currentPost.getCaption());
        txtOutput_Name.setText(currentPost.getName());
        txtFirstLetter.setText(String.valueOf(currentPost.getName().charAt(0)).toUpperCase());

        return  convertView;
    }
}
