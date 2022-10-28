package com.foodproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.foodproject.DBcontent;
import com.foodproject.DBupload;
import com.foodproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class BoardUploadActivity extends AppCompatActivity {
    String TAG = "Semofarm";

    private RelativeLayout mBack;
    private final int GALLERY_CODE = 10;
    ImageView photo;
    private FirebaseStorage storage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardwrite);
        initialize();
        setupWidgets();
        findViewById(R.id.addphoto_image).setOnClickListener(onClickListener);
        photo = (ImageView) findViewById(R.id.addphoto_image);
        storage = FirebaseStorage.getInstance();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addphoto_image:
                    doTakeMultiAlbumAction();
                    break;
            }
        }
    };

    private void doTakeMultiAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            if (data == null) {
            } else {
                Uri file = data.getData();
                StorageReference storageRef = storage.getReference();
                StorageReference riversRef = storageRef.child("photo/1.png");
                UploadTask uploadTask = riversRef.putFile(file);

                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    photo.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BoardUploadActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(BoardUploadActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        final EditText edit_name = findViewById(R.id.edit_name);
        //final EditText edit_price = findViewById(R.id.edit_price);
        final EditText edit_content = findViewById(R.id.edit_content);
        Button btn = findViewById(R.id.btn_submit);
        DBupload dao = new DBupload();
        btn.setOnClickListener(v ->
        {
            Log.d(TAG, "ClickListener!");
            DBcontent emp = new DBcontent(edit_name.getText().toString(), edit_content.getText().toString());
            Log.d(TAG, edit_name.getText().toString() + edit_content.getText().toString());
            dao.add(emp).addOnSuccessListener(suc ->
            {
                Log.d(TAG, "Success!");
                Toast.makeText(this, "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }).addOnFailureListener(er ->
            {
                Log.e(TAG, "Fail");
                Toast.makeText(this, "등록에 실패했습니다." + er.getMessage(), Toast.LENGTH_SHORT).show();
                onBackPressed();
            });
        });
    }

    private void initialize() {
        mBack = findViewById(R.id.back);
    }
    private void setupWidgets() {
        //change status bar color to transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.headerColor));
        }

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}