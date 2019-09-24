package com.example.damian.despegartest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCategorias = (ListView) findViewById(R.id.listaCategorias);
        listaCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ListaHotelesActivity.class);
                intent.putExtra(Constantes.CATEGORIA, view.getId());
                startActivity(intent);
            }
        });
        int[] categorias = new int[3];
        categorias[0] = Constantes.TODOS;
        categorias[1] = Constantes.ESTACIONAMIENTO;
        categorias[2] = Constantes.DESAYUNO;
        AdaptadorCategorias categoriasAdapter = new AdaptadorCategorias(this, categorias);
        listaCategorias.setAdapter(categoriasAdapter);
    }
}
