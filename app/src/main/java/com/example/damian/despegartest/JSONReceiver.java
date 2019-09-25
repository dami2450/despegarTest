package com.example.damian.despegartest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by damian on 23/10/2018.
 */
public class JSONReceiver extends ResultReceiver {
    Context context;
    ProgressDialog progress;
    String url;
    boolean individual;
    int categoria;
    static ArrayList<Comentario> comentariosArray;

    public JSONReceiver(Handler handler, Context context, ProgressDialog progress, String url, int categoria, boolean individual) {
        super(handler);
        this.context = context;
        this.progress = progress;
        this.url = url;
        this.categoria = categoria;
        this.individual = individual;
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        progress.dismiss();
        switch (resultCode) {
            case MyIntentService.DOWNLOAD_ERROR:
                Toast.makeText(context, "Error de conexion con la web",
                        Toast.LENGTH_SHORT).show();
                break;

            case MyIntentService.DOWNLOAD_SUCCESS:
                String datos = resultData.getString(Constantes.JSONDATA);
                DataParser parser = new DataParser();
                if (datos != null) {
                    if(individual){
                        final Hotel hotel = parser.parseHotel(datos);
                        HotelActivity.estrellas = hotel.getEstrellas();
                        ImageView foto = (ImageView) ((Activity) context).findViewById(R.id.foto);
                        foto.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, ImagenActivity.class);
                                intent.putExtra(Constantes.LINK, hotel.getLinkImagen());
                                context.startActivity(intent);
                            }
                        });
                        Picasso.get().load(hotel.getLinkImagen()).into(foto);
                        TextView nombre = (TextView) ((Activity) context).findViewById(R.id.nombre);
                        nombre.setText(hotel.getNombre());
                        TextView direccion = (TextView) ((Activity) context).findViewById(R.id.direccion);
                        direccion.setText(hotel.getDireccion());
                        TextView descripcion = (TextView) ((Activity) context).findViewById(R.id.descripcion);
                        descripcion.setText(hotel.getDescripcion());
                        TextView calificacion = (TextView) ((Activity) context).findViewById(R.id.calificacion);
                        String rating = context.getResources().getString(R.string.calificacion) + " " + hotel.getRating();
                        calificacion.setText(rating);
                        TextView ver_comentario = (TextView) ((Activity) context).findViewById(R.id.ver_comentario);
                        if(hotel.getComentarios().size() > 0){
                            ver_comentario.setVisibility(View.VISIBLE);
                            final String comentarios = "Comentarios (" + hotel.getComentarios().size() + ")";
                            ver_comentario.setText(comentarios);
                            ver_comentario.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    comentariosArray = hotel.getComentarios();
                                    Intent intent = new Intent(context,ComentariosActivity.class);
                                    context.startActivity(intent);
                                }
                            });
                        }else {
                            ver_comentario.setVisibility(View.GONE);
                        }
                    }else{
                        ArrayList<Hotel> hoteles = parser.parseHoteles(datos,categoria);
                        AdaptadorHoteles adaptadorHoteles = new AdaptadorHoteles(context,hoteles);
                        ListView listView = (ListView) ((Activity) context).findViewById(R.id.listaHoteles);
                        listView.setAdapter(adaptadorHoteles);
                    }
                } else {
                    Toast.makeText(context,
                            "Error de conexion con la web",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onReceiveResult(resultCode, resultData);
    }

}