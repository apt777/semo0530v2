package com.foodproject.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.foodproject.R;
import com.foodproject.model.Food;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;


public class FoodUploadActivity extends AppCompatActivity {
    private RelativeLayout mBack;
    ImageView chooseImage;
    EditText product, price, location;
    Button add;
    DatabaseReference root= FirebaseDatabase.getInstance().getReference("Foods");
    StorageReference reference= FirebaseStorage.getInstance().getReference("Foods Image");
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodwrite);
        initialize();
        setupWidgets();
        chooseImage=findViewById(R.id.addphoto_image);
        add=findViewById(R.id.btn_submit);
        product=findViewById(R.id.edit_product);
        price=findViewById(R.id.edit_price);
        location=findViewById(R.id.edit_location);
        chooseImage.setOnClickListener(view -> {
            Intent galleryIntent=new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,2);
        });
        add.setOnClickListener(view -> {
            if (imageUri!=null){
                uploadToFirebase(imageUri);
                add.setEnabled(false);
            }
            else{
                Toast.makeText(FoodUploadActivity.this, "사진을 선택해주세요", Toast.LENGTH_SHORT).show();
            }
        });
        product.addTextChangedListener(textWatcher);
        price.addTextChangedListener(textWatcher);
        location.addTextChangedListener(textWatcher);

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
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String name=product.getText().toString().trim();
            add.setEnabled(!name.isEmpty());
            String prices=price.getText().toString().trim();
            add.setEnabled(!prices.isEmpty());
            String locate=location.getText().toString().trim();
            add.setEnabled(!locate.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void uploadToFirebase(Uri uri) {
        final StorageReference fileRef=reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Food Food=new Food(uri.toString(),product.getText().toString(),
                                (long) Integer.parseInt(price.getText().toString()),location.getText().toString());
                        String modelId=root.push().getKey();
                        root.child(modelId).setValue(Food);
                        Toast.makeText(FoodUploadActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        product.getText().clear();
                        price.getText().clear();
                        location.getText().clear();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK && data !=null){
            imageUri=data.getData();
            chooseImage.setImageURI(imageUri);
        }
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(mUri));
    }
}