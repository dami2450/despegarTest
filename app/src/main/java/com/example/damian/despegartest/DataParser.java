package com.example.damian.despegartest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
