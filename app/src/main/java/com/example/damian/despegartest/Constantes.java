package com.example.damian.despegartest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class Constantes {
    public static final String CATEGORIA = "CATEGORIA";
    public static final String ID = "ID";
    public static final String LINK = "LINK";
    public static final String JSONDATA = "jsonData";
    public static final String RECEIVER = "receiver";
    public static final String ESTRELLAS = "estrellas";
    public static final String URL = "url";
    public static final String URL_BASE = "http://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/hotels";
    public static final int TODOS = 0;
    public static final int ESTACIONAMIENTO = 1;
    public static final int DESAYUNO = 2;

    public static void llenarListaHoteles(Context context, String buscandoString, String url, int categoria){
        ProgressDialog progressDialog = ProgressDialog.show(context, "", buscandoString, true, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        JSONReceiver jsonReceiver = new JSONReceiver(new Handler(),context,progressDialog,url,categoria,false);
        Intent startIntent = new Intent(context,MyIntentService.class);
        startIntent.putExtra(RECEIVER, jsonReceiver);
        startIntent.putExtra(URL, url);
        context.startService(startIntent);
    }

    public static void llenarHotel(Context context, String buscandoString, String url){
        ProgressDialog progressDialog = ProgressDialog.show(context, "", buscandoString, true, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        JSONReceiver jsonReceiver = new JSONReceiver(new Handler(),context,progressDialog,url,-1,true);
        Intent startIntent = new Intent(context,MyIntentService.class);
        startIntent.putExtra(RECEIVER, jsonReceiver);
        startIntent.putExtra(URL, url);
        context.startService(startIntent);
    }

    public static String decodificar(String original){
        String resultado = "";
        try {
            byte[] byteArray = original.getBytes("UTF-8");
            resultado = new String(byteArray,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
