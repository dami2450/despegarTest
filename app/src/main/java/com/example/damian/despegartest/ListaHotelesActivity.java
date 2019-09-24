package com.example.damian.despegartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ListaHotelesActivity extends AppCompatActivity {
    ListView listaHoteles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);
        int categoria = getIntent().getIntExtra(Constantes.CATEGORIA, -1);
        String titulo = "";
        switch (categoria){
            case Constantes.TODOS:
                titulo = getResources().getString(R.string.todos);
                break;
            case Constantes.ESTACIONAMIENTO:
                titulo = getResources().getString(R.string.con_estacionamiento);
                break;
            case Constantes.DESAYUNO:
                titulo = getResources().getString(R.string.con_desayuno);
                break;
        }
        getSupportActionBar().setTitle(titulo);
        listaHoteles = (ListView) findViewById(R.id.listaHoteles);
        Constantes.llenarListaHoteles(this,getResources().getString(R.string.buscando_hoteles),Constantes.URL_BASE,listaHoteles,categoria);
    }
}