package com.example.damian.despegartest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorComentarios extends BaseAdapter {
    private Context context;
    private ArrayList<Comentario> comentarios;

    public AdaptadorComentarios(Context context, ArrayList<Comentario> comentarios){
        this.context = context;
        this.comentarios = comentarios;
    }

    @Override
    public int getCount() {
        return comentarios.size();
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
        row = inflater.inflate(R.layout.item_comentario, parent, false);
        TextView usuario = row.findViewById(R.id.usuario);
        TextView titulo = row.findViewById(R.id.titulo);
        TextView bueno = row.findViewById(R.id.bueno);
        TextView malo = row.findViewById(R.id.malo);
        ImageView logoBuenoMalo = row.findViewById(R.id.logoBuenoMalo);
        Comentario comentario = comentarios.get(i);
        usuario.setText(comentario.getNombreUsuario());
        if(comentario.getTitulo() != null && comentario.getTitulo().length() > 0){
            titulo.setVisibility(View.VISIBLE);
            titulo.setText(comentario.getTitulo());
        }else{
            titulo.setVisibility(View.GONE);
        }
        boolean esBueno = comentario.getBueno() != null && comentario.getBueno().length() > 0;
        boolean esMalo = comentario.getMalo() != null && comentario.getMalo().length() > 0;
        if(esBueno){
            bueno.setVisibility(View.VISIBLE);
            String loBueno = context.getResources().getString(R.string.a_favor) + " " + comentario.getBueno();
            bueno.setText(loBueno);
            if(esMalo){
                logoBuenoMalo.setImageResource(R.drawable.bueno_malo);
            }else{
                logoBuenoMalo.setImageResource(R.drawable.bueno);
            }
        }else{
            bueno.setVisibility(View.GONE);
        }
        if(esMalo){
            malo.setVisibility(View.VISIBLE);
            String loMalo = context.getResources().getString(R.string.en_contra) + " " + comentario.getMalo();
            malo.setText(loMalo);
            if(esBueno){
                logoBuenoMalo.setImageResource(R.drawable.bueno_malo);
            }else{
                logoBuenoMalo.setImageResource(R.drawable.malo);
            }
        }else{
            malo.setVisibility(View.GONE);
        }
        return row;
    }
}

