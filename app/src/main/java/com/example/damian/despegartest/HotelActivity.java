package com.example.damian.despegartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelActivity extends AppCompatActivity {
    ImageView foto, facebook, twitter, instagram;
    TextView nombre, descripcion, direccion, telefono, paginaWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        /*int id = getIntent().getIntExtra(Constantes.ID, -1);
        String url = "https://barrioshoprest.webcindario.com/listaHoteles.php";
        foto = (ImageView) findViewById(R.id.foto);
        facebook = (ImageView) findViewById(R.id.facebook);
        twitter = (ImageView) findViewById(R.id.twitter);
        instagram = (ImageView) findViewById(R.id.instagram);
        nombre = (TextView) findViewById(R.id.nombre);
        descripcion = (TextView) findViewById(R.id.descripcion);
        direccion = (TextView) findViewById(R.id.direccion);
        telefono = (TextView) findViewById(R.id.telefono);
        paginaWeb = (TextView) findViewById(R.id.paginaWeb);
        Constantes.llenarHotel(this,getResources().getString(R.string.buscando_hoteles),url,foto,facebook,twitter,instagram,nombre,descripcion,direccion,telefono,paginaWeb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detalle, menu);
        itemFavoritos = menu.findItem(R.id.menuFavorito);
        itemMapa = menu.findItem(R.id.menuUbicar);
        itemRanking = menu.findItem(R.id.menuRating);
        switch(modoPublicacion){
            case -1:
                switch(Constantes.getTipoGrilla(this)){
                    case(Constantes.TODAS_LAS_OFERTAS):
                        itemFavoritos.setVisible(false);
                        itemRanking.setVisible(false);
                        itemMapa.setVisible(true);
                        break;
                    case(Constantes.PRODUCTOS):
                        itemFavoritos.setVisible(true);
                        itemRanking.setVisible(false);
                        itemMapa.setVisible(false);
                        if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS)){
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                    case(Constantes.SERVICIOS):
                        itemFavoritos.setVisible(true);
                        itemRanking.setVisible(true);
                        itemMapa.setVisible(false);
                        if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_SERVICIOS_FAVORITOS)){
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                    default:
                        itemFavoritos.setVisible(true);
                        itemRanking.setVisible(true);
                        itemMapa.setVisible(true);
                        if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS)){
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                }
                break;
            case Constantes.MODO_LOCAL:
                itemFavoritos.setVisible(true);
                itemRanking.setVisible(true);
                itemMapa.setVisible(true);
                if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS)){
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                }else{
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                }
                break;
            case Constantes.MODO_PRODUCTO:
                itemFavoritos.setVisible(true);
                itemRanking.setVisible(false);
                itemMapa.setVisible(false);
                if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS)){
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                }else{
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                }
                break;
            case Constantes.MODO_SERVICIO:
                itemFavoritos.setVisible(true);
                itemRanking.setVisible(true);
                itemMapa.setVisible(false);
                if(Constantes.esFavorito(idFav,this, DatabaseFinder.TABLA_SERVICIOS_FAVORITOS)){
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                }else{
                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                }
                break;
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case (R.id.menuRating):
                Intent intentRanking = new Intent(this,RankingLocalActivity.class);
                intentRanking.putExtra(Constantes.ID, idGeneral);
                startActivity(intentRanking);
                break;
            case(R.id.menuFavorito):
                switch(modoPublicacion){
                    case(-1):
                        switch(Constantes.getTipoGrilla(this)){
                            case(Constantes.PRODUCTOS):
                                if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS)){
                                    Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                                }else{
                                    Constantes.removerID(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                                }
                                break;
                            case(Constantes.SERVICIOS):
                                if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS)){
                                    Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                                }else{
                                    Constantes.removerID(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                                }
                                break;
                            default:
                                if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS)){
                                    Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                                }else{
                                    Constantes.removerID(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS);
                                    itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                                }
                                break;
                        }
                        break;
                    case Constantes.MODO_LOCAL:
                        if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS)){
                            Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            Constantes.removerID(idFav,this,DatabaseFinder.TABLA_NEGOCIOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                    case Constantes.MODO_PRODUCTO:
                        if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS)){
                            Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            Constantes.removerID(idFav,this,DatabaseFinder.TABLA_PRODUCTOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                    case Constantes.MODO_SERVICIO:
                        if(!Constantes.esFavorito(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS)){
                            Constantes.cargarID(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_seleccionado));
                        }else{
                            Constantes.removerID(idFav,this,DatabaseFinder.TABLA_SERVICIOS_FAVORITOS);
                            itemFavoritos.setIcon(getResources().getDrawable(R.mipmap.ic_favorito_no_seleccionado));
                        }
                        break;
                }
                break;
            case (R.id.menuUbicar):
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra(Constantes.MARKERINDIVIDUAL, true);
                intent.putExtra(Constantes.ID, idGeneral);
                intent.putExtra(Constantes.CODIGO, getIntent().getIntExtra(Constantes.CODIGO, 0));
                intent.putExtra(Constantes.RUBRO, getIntent().getStringExtra(Constantes.RUBRO));
                startActivity(intent);
                break;
            case (R.id.menuLlamar):
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    llamar();
                }else{
                    ArrayList<String> permisos = new ArrayList<String>();
                    permisos.add(Manifest.permission.CALL_PHONE);
                    String[] permisosVector = permisos.toArray(new String[permisos.size()]);
                    ActivityCompat.requestPermissions(this,permisosVector, Constantes.CODIGO_PERMISO);
                }
                break;
        }
        return true;*/
    }
}
