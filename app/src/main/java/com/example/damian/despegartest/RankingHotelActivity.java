package com.example.damian.despegartest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by damian on 5/2/2018.
 */
public class RankingHotelActivity extends Activity {
    ImageView estrellaUno, estrellaDos, estrellaTres, estrellaCuatro, estrellaCinco;
    int estrellas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Display display = getWindowManager().getDefaultDisplay();

        int height = 0;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            params.width = (int) (display.getWidth() * 0.6); //fixed width
            params.height = (int) (display.getWidth() * 0.6); //fixed width
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            params.width = (int) (display.getWidth() * 0.8); //fixed width
            params.height = (int) (display.getWidth() * 0.8); //fixed width
        }
        params.alpha = 1.0f;
        params.dimAmount = 0.5f;
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.layout_menu_dialogo);
        View actionBar =  getActionBar().getCustomView();
        ImageView home = (ImageView) actionBar.findViewById(R.id.home_icono_actionbar);
        TextView titulo = (TextView) actionBar.findViewById(R.id.actionbar_title);
        titulo.setText("ESTRELLAS");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_ranking_hotel);
        estrellaUno = (ImageView) findViewById(R.id.estrellaUno);
        estrellaDos = (ImageView) findViewById(R.id.estrellaDos);
        estrellaTres = (ImageView) findViewById(R.id.estrellaTres);
        estrellaCuatro = (ImageView) findViewById(R.id.estrellaCuatro);
        estrellaCinco = (ImageView) findViewById(R.id.estrellaCinco);
        estrellas = getIntent().getIntExtra(Constantes.ESTRELLAS, -1);
        llenarEstrellas();
    }

    public void llenarEstrellas() {
        switch (estrellas){
            case 1:
                estrellaUno.setImageResource(R.drawable.estrella_llena);
                estrellaDos.setImageResource(R.drawable.estrella_vacia);
                estrellaTres.setImageResource(R.drawable.estrella_vacia);
                estrellaCuatro.setImageResource(R.drawable.estrella_vacia);
                estrellaCinco.setImageResource(R.drawable.estrella_vacia);
                break;
            case 2:
                estrellaUno.setImageResource(R.drawable.estrella_llena);
                estrellaDos.setImageResource(R.drawable.estrella_llena);
                estrellaTres.setImageResource(R.drawable.estrella_vacia);
                estrellaCuatro.setImageResource(R.drawable.estrella_vacia);
                estrellaCinco.setImageResource(R.drawable.estrella_vacia);
                break;
            case 3:
                estrellaUno.setImageResource(R.drawable.estrella_llena);
                estrellaDos.setImageResource(R.drawable.estrella_llena);
                estrellaTres.setImageResource(R.drawable.estrella_llena);
                estrellaCuatro.setImageResource(R.drawable.estrella_vacia);
                estrellaCinco.setImageResource(R.drawable.estrella_vacia);
                break;
            case 4:
                estrellaUno.setImageResource(R.drawable.estrella_llena);
                estrellaDos.setImageResource(R.drawable.estrella_llena);
                estrellaTres.setImageResource(R.drawable.estrella_llena);
                estrellaCuatro.setImageResource(R.drawable.estrella_llena);
                estrellaCinco.setImageResource(R.drawable.estrella_vacia);
                break;
            case 5:
                estrellaUno.setImageResource(R.drawable.estrella_llena);
                estrellaDos.setImageResource(R.drawable.estrella_llena);
                estrellaTres.setImageResource(R.drawable.estrella_llena);
                estrellaCuatro.setImageResource(R.drawable.estrella_llena);
                estrellaCinco.setImageResource(R.drawable.estrella_llena);
                break;
            default:
                estrellaUno.setImageResource(R.drawable.estrella_vacia);
                estrellaDos.setImageResource(R.drawable.estrella_vacia);
                estrellaTres.setImageResource(R.drawable.estrella_vacia);
                estrellaCuatro.setImageResource(R.drawable.estrella_vacia);
                estrellaCinco.setImageResource(R.drawable.estrella_vacia);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
