package com.example.damian.despegartest;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DataParser {

    public ArrayList<Hotel> parseHoteles(String jsonData, int categoria)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        ArrayList<Hotel> hoteles = new ArrayList<>();

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("items");
            for(int i = 0; i < jsonArray.length(); i++){
                boolean estacionamiento = false;
                boolean desayuno = false;
                JSONObject hotel = jsonArray.getJSONObject(i);
                Hotel hotelAAgregar = new Hotel();
                hotelAAgregar.setLinkImagen(hotel.getString("main_picture"));
                hotelAAgregar.setNombre(hotel.getString("name"));
                hotelAAgregar.setDireccion(hotel.getString("address"));
                hotelAAgregar.setEstrellas(hotel.getInt("stars"));
                hotelAAgregar.setId(hotel.getInt("id"));
                hotelAAgregar.setRating(hotel.getDouble("rating"));
                JSONArray beneficios = hotel.getJSONArray("amenities");
                for(int j = 0; j < beneficios.length(); j++){
                    JSONObject beneficio = beneficios.getJSONObject(j);
                    Beneficio beneficioAAGregrar = new Beneficio();
                    beneficioAAGregrar.setDescripcion(beneficio.getString("description"));
                    String id = beneficio.getString("id");
                    beneficioAAGregrar.setId(id);
                    hotelAAgregar.addBeneficio(beneficioAAGregrar);
                    desayuno = desayuno || id.equals("BREAKFST");
                    estacionamiento = estacionamiento || id.equals("PARKING");
                }
                JSONObject precio = hotel.getJSONObject("price");
                hotelAAgregar.setPrecioFinal(precio.getBoolean("final_price"));
                JSONObject currency = precio.getJSONObject("currency");
                String precioString = currency.getString("mask") + precio.getInt("best") + " "  + currency.getString("code");
                hotelAAgregar.setPrecio(precioString);
                if(categoria == Constantes.TODOS || (categoria == Constantes.ESTACIONAMIENTO  && estacionamiento) || (categoria == Constantes.DESAYUNO && desayuno)){
                    hoteles.add(hotelAAgregar);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hoteles;
    }

    public Hotel parseHotel(String jsonData)
    {
        Hotel hotel = new Hotel();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectHotel = jsonObject.getJSONObject("hotel");
            hotel.setId(jsonObjectHotel.getInt("id"));
            hotel.setDescripcion(jsonObjectHotel.getString("description"));
            hotel.setEstrellas(jsonObjectHotel.getInt("stars"));
            hotel.setNombre(jsonObjectHotel.getString("name"));
            hotel.setDireccion(jsonObjectHotel.getString("address"));
            hotel.setLinkImagen(jsonObjectHotel.getString("main_picture"));
            hotel.setRating(jsonObjectHotel.getDouble("rating"));
            JSONObject localizacion = jsonObjectHotel.getJSONObject("geo_location");
            hotel.setLat(localizacion.getDouble("latitude"));
            hotel.setLng(localizacion.getDouble("longitude"));
            JSONObject ciudad = jsonObjectHotel.getJSONObject("city");
            hotel.setCiudad(ciudad.getString("name"));
            JSONObject pais = ciudad.getJSONObject("country");
            hotel.setPais(pais.getString("name"));
            JSONObject divisionAdministrativa = ciudad.getJSONObject("administrative_division");
            hotel.setDivisionAdministrativa(divisionAdministrativa.getString("name"));
            JSONArray comentarios = jsonObjectHotel.getJSONArray("reviews");
            for(int j = 0; j < comentarios.length(); j++){
                JSONObject comentario = comentarios.getJSONObject(j).getJSONObject("comments");
                Comentario comentarioAAGregrar = new Comentario();
                if(comentario.has("title")){
                    comentarioAAGregrar.setTitulo(comentario.getString("title"));
                }
                if(comentario.has("good")){
                    comentarioAAGregrar.setBueno(comentario.getString("good"));
                }
                if(comentario.has("bad")){
                    comentarioAAGregrar.setMalo(comentario.getString("bad"));
                }
                JSONObject usuario = comentarios.getJSONObject(j).getJSONObject("user");
                comentarioAAGregrar.setNombreUsuario(usuario.getString("name"));
                hotel.addComentarios(comentarioAAGregrar);
            }
            JSONObject precioJSON = jsonObject.getJSONObject("price");
            hotel.setPrecioFinal(precioJSON.getBoolean("final_price"));
            JSONObject currency = precioJSON.getJSONObject("currency");
            String precio = currency.getString("mask") + precioJSON.getInt("best") + " " + currency.getString("code");
            hotel.setPrecio(precio);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hotel;
    }
}
