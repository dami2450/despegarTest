package com.example.damian.despegartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelActivity extends AppCompatActivity {
    public static int estrellas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        int id = getIntent().getIntExtra(Constantes.ID, -1);
        String url = Constantes.URL_BASE + "/" + id;
        Constantes.llenarHotel(this,getResources().getString(R.string.cargando_hotel),url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuRating):
                Intent intentRanking = new Intent(this,RankingHotelActivity.class);
                intentRanking.putExtra(Constantes.ESTRELLAS, estrellas);
                startActivity(intentRanking);
                break;
        }
        return true;
    }
}
