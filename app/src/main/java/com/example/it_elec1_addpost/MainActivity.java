package com.example.it_elec1_addpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    ListView lstPost;
    EditText txtName;
    EditText txtCaption;
    Button btnAddPost;
    Post post;
    ArrayList<Post> arrPost;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstPost = findViewById(R.id.lstPost);
        txtName = findViewById(R.id.txtName);
        txtCaption = findViewById(R.id.txtCaption);
        btnAddPost = findViewById(R.id.btnAddPost);

        btnAddPost.setOnClickListener(this);

        arrPost = new ArrayList<>();
        post = new Post();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddPost:
                post.setName(txtName.getText().toString());
                post.setCaption(txtCaption.getText().toString());
                arrPost.add(post);
                adapter = new PostAdapter(this, arrPost);
                lstPost.setAdapter(adapter);
                break;
        }
    }
}