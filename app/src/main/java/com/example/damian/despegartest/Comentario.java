package com.example.damian.despegartest;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Comentario {
    String bueno;
    String malo;
    String titulo;
    String nombreUsuario;

    public String getBueno() {
        return bueno;
    }

    public void setBueno(String bueno) {
        this.bueno = bueno;
    }

    public String getMalo() {
        return malo;
    }

    public void setMalo(String malo) {
        this.malo = malo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
