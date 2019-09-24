package com.example.damian.despegartest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorCategorias extends BaseAdapter {
    private Context context;
    private int[] categorias;

    public AdaptadorCategorias(Context context, int[] categorias){
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final View row;
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.item_categoria, parent, false);
        final TextView descripcion;
        final ImageView imagen;
        descripcion = (TextView) row.findViewById(R.id.descripcion);
        imagen = (ImageView) row.findViewById(R.id.imagenCategoria);
        int categoria = categorias[i];
        switch (categoria){
            case(Constantes.TODOS):
                imagen.setImageResource(R.mipmap.menu_todos);
                descripcion.setText(context.getResources().getString(R.string.todos));
                break;
            case(Constantes.ESTACIONAMIENTO):
                imagen.setImageResource(R.mipmap.menu_estacionamiento);
                descripcion.setText(context.getResources().getString(R.string.con_estacionamiento));
                break;
            case(Constantes.DESAYUNO):
                imagen.setImageResource(R.mipmap.menu_desayuno);
                descripcion.setText(context.getResources().getString(R.string.con_desayuno));
                break;
        }
        row.setId(categoria);
        return row;
    }
}

