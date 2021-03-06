package com.example.it_elec1_addpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    ListView lstPost;
    EditText editTextAddCaption;
    EditText txtCaption;
    Button btnAddPost;
    Button btnBrowse;
    ImageView imgPost;
    Post post;
    ArrayList<Post> arrPost;
    PostAdapter adapter;
    Intent intent;
    int RESULT_LOAD_IMAGE = 1;
    Date date = new Date();
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        lstPost = findViewById(R.id.lstPost);
        editTextAddCaption = findViewById(R.id.editTextAddCaption);
        btnAddPost = findViewById(R.id.btnAddPost);
        btnBrowse = findViewById(R.id.btnBrowse);
        imgPost = findViewById(R.id.imgPost);

        btnAddPost.setOnClickListener(this);
        btnBrowse.setOnClickListener(this);

        arrPost = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnAddPost) {
            post = new Post();
            post.setCaption(editTextAddCaption.getText().toString());
            post.setDateTime(DateFormat.getDateInstance().format(date));
            post.setImgPath(path);
            arrPost.add(post);
        }
        else if(view.getId() == R.id.btnBrowse) {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }


        adapter = new PostAdapter(this, arrPost, new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClickLike(View v, int position) {
                LikeClicked(position);
            }

            @Override
            public void onItemClickDelete(int position) {
                DeleteClicked(position);
            }
        });
        lstPost.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            ((Cursor) cursor).moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            path = picturePath;
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgPost);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void LikeClicked(int position) {
        Post newPost = arrPost.get(position);
        newPost.setLike( newPost.getLike() == 0 ? 1 : 0);
        arrPost.set(position, newPost);
        adapter.notifyDataSetChanged();
    }

    public void DeleteClicked(int position) {
        arrPost.remove(position);
        adapter.notifyDataSetChanged();
    }
}