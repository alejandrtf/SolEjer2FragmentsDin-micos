package com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt.model.LinkData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LinkListFragment.OnListFragmentSelectionListener {
    //lo usaré para guardar el estado del fragment detalle
    private static final String ID_WEBVIEW_FRAGMET = WebViewFragment.class.getSimpleName();
   public static ArrayList<LinkData.TutorialAndroid> listaDatos=new ArrayList<>();

    private WebViewFragment wvFragment;
    public static String[]titulos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dual_panel_wide);

        //cargo datos de los recursos
        LinkData.inicializarItemsFromResources(getResources().getStringArray(R.array.lista_enlaces_tutoriales_Android),
                getResources().getStringArray(R.array.urls_enlaces_tutoriales_Android));

        //llamAR CARGAR DATOS EN EL ARRAYLIST listaDatos
       // cargarDatos();

        //obtengo el segundo panel




    }




    @Override
    public void onListFragmentSelection(String tituloElegido) {
        String url=LinkData.ITEM_MAP.get(tituloElegido).getEnlaceContenido();

        wvFragment = (WebViewFragment) getSupportFragmentManager().findFragmentById(R.id.webFragment);
        //muestro la url
        if (!wvFragment.getActualUrl().equals(url))
            wvFragment.mostrarUrl(url);


    }



}
