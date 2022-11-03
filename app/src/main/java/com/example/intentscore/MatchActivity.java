package com.example.intentscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView textHome;

    private TextView scoreHome;

    private ImageView homeLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        textHome = findViewById(R.id.txt_home);

        homeLogo = findViewById(R.id.home_logo);

        scoreHome = findViewById(R.id.score_home);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        Intent intent = getIntent();
        String home = intent.getStringExtra("home_team");
        textHome.setText(home);
        Bitmap imgHome = (Bitmap) intent.getParcelableExtra("image_home_bitmap");
        homeLogo.setImageBitmap(imgHome);

    }

    public void handlerAddHome(View view) {
        int lastScore = Integer.parseInt(scoreHome.getText().toString());
        String score = String.valueOf(lastScore + 1);
        scoreHome.setText(score);
    }
}