package com.example.it_elec1_addpost;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {
    private OnItemClickListener mOnItemClick;
    private Comments comment;
    private ArrayList<Comments> arrComments = new ArrayList<>();
    private CommentsAdapter adapter;
    private Context context;

    public interface OnItemClickListener {
        public void onItemClickLike(View v, int position);
        public void onItemClickDelete(int position);
    }

    public PostAdapter(Context context, ArrayList<Post> arrPost, OnItemClickListener onItemClick ) {
        super(context, 0, arrPost);
        this.context = context;
        mOnItemClick = onItemClick;
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
        ImageView imgPost = convertView.findViewById(R.id.imgOutput_Post);
        Button btnLike = convertView.findViewById(R.id.btnLike);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        ListView lstPost = convertView.findViewById(R.id.lstPost);
        Button btnComment = convertView.findViewById(R.id.btnComment);
        ListView lstComment = convertView.findViewById(R.id.lstComment);
        LinearLayout llComment = convertView.findViewById(R.id.llComment);
        EditText editText_AddComment = convertView.findViewById(R.id.editTxt_AddComment);
        Button btnAddComment = convertView.findViewById(R.id.btnAddComment);


        if(currentPost.getLike() == 0)
        {
            btnLike.setText("Like");
            btnLike.setBackgroundColor(Color.parseColor("#FF3700B3"));
        }
        else
        {
            btnLike.setText("Liked");
            btnLike.setBackgroundColor(Color.parseColor("#24db76"));
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClick.onItemClickLike(view, position);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClick.onItemClickDelete(position);
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llComment.getVisibility() == View.VISIBLE) {
                    llComment.setVisibility(View.INVISIBLE);
                }
                else{
                    llComment.setVisibility(View.VISIBLE);
                    btnAddComment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            comment = new Comments();
                            comment.setPosition(position);
                            comment.setComments(editText_AddComment.getText().toString());
                            comment.setComment(editText_AddComment.getText().toString());
                            arrComments.add(comment);
                            adapter = new CommentsAdapter(context, arrComments);
                            lstComment.setAdapter(adapter);
                        }
                    });
                }
            }
        });

        txtDateTIme.setText(currentPost.getDateTime());
        txtOutput_Caption.setText(currentPost.getCaption());
        txtOutput_Name.setText(currentPost.getName());
        txtFirstLetter.setText(String.valueOf(currentPost.getName().charAt(0)).toUpperCase());
        imgPost.setImageBitmap(BitmapFactory.decodeFile(currentPost.getImgPath()));

        return  convertView;
    }
}
