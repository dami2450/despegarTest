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

public class AdaptadorHoteles extends BaseAdapter {
    private Context context;
    private ArrayList<Hotel> hoteles;

    public AdaptadorHoteles(Context context, ArrayList<Hotel> hoteles){
        this.context = context;
        this.hoteles = hoteles;
    }

    @Override
    public int getCount() {
        return hoteles.size();
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
        row = inflater.inflate(R.layout.item_hotel, parent, false);
        final ImageView imagen;
        final TextView nombre;
        final TextView direccion;
        final TextView rating;
        imagen = (ImageView) row.findViewById(R.id.imagen);
        nombre = (TextView) row.findViewById(R.id.nombre);
        direccion = (TextView) row.findViewById(R.id.direccion);
        rating = (TextView) row.findViewById(R.id.rating);
        Hotel hotel = hoteles.get(i);
        Picasso.get().load(hotel.getLinkImagen()).into(imagen);
        nombre.setText(hotel.getNombre());
        direccion.setText(hotel.getDireccion());
        String ratingString = "" + hotel.getRating();
        rating.setText(ratingString);
        final int id = hotel.getId();
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HotelActivity.class);
                intent.putExtra(Constantes.ID, id);
                context.startActivity(intent);
            }
        });
        return row;
    }
}

