package com.example.damian.despegartest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Hotel {
    String linkImagen;
    String nombre;
    String direccion;
    String descripcion;
    String ciudad;
    String pais;
    String divisionAdministrativa;
    ArrayList<Beneficio> beneficios = new ArrayList<Beneficio>();
    ArrayList<Comentario> comentarios  = new ArrayList<Comentario>();
    boolean precioFinal;
    int estrellas;
    int id;
    double rating;
    double lat;
    double lng;
    String precio;

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void addBeneficio(Beneficio beneficio) {
        beneficios.add(beneficio);
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void addComentarios(Comentario comentario) {
        comentarios.add(comentario);
    }

    public boolean isPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(boolean precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDivisionAdministrativa() {
        return divisionAdministrativa;
    }

    public void setDivisionAdministrativa(String divisionAdministrativa) {
        this.divisionAdministrativa = divisionAdministrativa;
    }
}
