package com.example.damian.despegartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImagenActivity extends AppCompatActivity {
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String imagen = getIntent().getStringExtra(Constantes.LINK);
        foto = (ImageView) findViewById(R.id.foto);
        Picasso.get().load(imagen).into(foto);
    }
}
