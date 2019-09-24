package com.example.damian.despegartest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Constantes {
    public static final String CATEGORIA = "CATEGORIA";
    public static final String ID = "ID";
    public static final String URL_BASE = "http://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels";
    public static final int TODOS = 0;
    public static final int ESTACIONAMIENTO = 1;
    public static final int DESAYUNO = 2;

    public static void llenarListaHoteles(Context context, String buscandoString, String url, ListView listView, int categoria){
        ProgressDialog progressDialog = ProgressDialog.show(context, "", buscandoString, true, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        JSONReceiver jsonReceiver = new JSONReceiver(new Handler(),context,progressDialog,url,listView,categoria);
        Intent startIntent = new Intent(context,MyIntentService.class);
        startIntent.putExtra("receiver", jsonReceiver);
        startIntent.putExtra("url", url);
        context.startService(startIntent);
    }

    public static void llenarHotel(Context context, String buscandoString, String url, ImageView foto, ImageView facebook, ImageView twitter, ImageView instagram, TextView nombre, TextView descripcion, TextView direccion, TextView telefono, TextView paginaWeb){
        ProgressDialog progressDialog = ProgressDialog.show(context, "", buscandoString, true, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        JSONReceiver jsonReceiver = new JSONReceiver(new Handler(),context,progressDialog,url,foto,facebook,twitter,instagram,nombre,descripcion,direccion,telefono,paginaWeb);
        Intent startIntent = new Intent(context,MyIntentService.class);
        startIntent.putExtra("receiver", jsonReceiver);
        startIntent.putExtra("url", url);
        context.startService(startIntent);
    }
}
