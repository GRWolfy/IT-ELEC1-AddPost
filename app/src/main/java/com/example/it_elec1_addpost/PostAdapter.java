package com.example.it_elec1_addpost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> arrPost){
        super(context, 0, arrPost);
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent){
        Post currentPost = getItem(index);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }



        return  convertView;
    }
}
