package com.example.damian.despegartest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by damian on 23/10/2018.
 */
public class JSONReceiver extends ResultReceiver {
    Context context;
    ProgressDialog progress;
    String url;
    ListView listView;
    ImageView foto, facebook, twitter, instagram;
    TextView nombre, descripcion, direccion, telefono, paginaWeb;
    boolean individual;
    int categoria;

    public JSONReceiver(Handler handler, Context context, ProgressDialog progress, String url, ListView listView, int categoria) {
        super(handler);
        this.context = context;
        this.progress = progress;
        this.url = url;
        this.listView = listView;
        this.categoria = categoria;
        individual = false;
    }

    public JSONReceiver(Handler handler, Context context, ProgressDialog progress, String url, ImageView foto, ImageView facebook, ImageView twitter, ImageView instagram, TextView nombre, TextView descripcion, TextView direccion, TextView telefono, TextView paginaWeb) {
        super(handler);
        this.context = context;
        this.progress = progress;
        this.url = url;
        this.foto = foto;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;
        individual = true;
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
                String datos = resultData.getString("jsonData");
                DataParser parser = new DataParser();
                if (datos != null) {
                    if(individual){

                    }else{
                        ArrayList<Hotel> hoteles = parser.parseHoteles(datos,categoria);
                        AdaptadorHoteles adaptadorHoteles = new AdaptadorHoteles(context,hoteles);
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