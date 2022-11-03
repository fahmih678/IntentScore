package com.example.intentscore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE_IMAGE_ONE = 1;
    private static final String TAG = MainActivity.class.getCanonicalName();

    public Bitmap bitmap;
    private ImageView imgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team (tidak boleh input kosong, tidak boleh sama dengan input away, dilakukan di handlerNext)
        //2. Validasi Input Away Team (tidak boleh input kosong, tidak boleh sama dengan input home, dilakukan di handlerNext)
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity

        imgHome = findViewById(R.id.home_logo);

    }

    public void handlerNext(View view) {


        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("home_team", "Ubahlah text ini sesuai input home");
        // past image home to next activity
        if(imgHome != null){
            imgHome.buildDrawingCache();
            Bitmap bitmap = imgHome.getDrawingCache();
            intent.putExtra("image_home_bitmap", bitmap);
        }

        startActivity(intent);
    }

    public void handlerImageHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("img_for", "home");
        startActivityForResult(intent, GALLERY_REQUEST_CODE_IMAGE_ONE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // Request image dari gallery
        if (resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    // if img home / set image
                    if(requestCode == GALLERY_REQUEST_CODE_IMAGE_ONE){
                        imgHome.setImageBitmap(bitmap);
                    }
                    // if img away / set image

                    } catch (IOException e) {
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}