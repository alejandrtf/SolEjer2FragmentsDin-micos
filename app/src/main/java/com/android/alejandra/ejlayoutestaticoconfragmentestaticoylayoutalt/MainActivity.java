package com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt.model.LinkData;

public class MainActivity extends AppCompatActivity implements LinkListFragment.OnListFragmentSelectionListener {
    //lo usaré para guardar el estado del fragment detalle
    private static final String ID_WEBVIEW_FRAGMET = WebViewFragment.class.getSimpleName();

    private FrameLayout listFragmentContenedor, webFragmentContenedor;
    private FragmentManager fragmentManager;

    private WebViewFragment wvFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //cargo datos de los recursos
        LinkData.inicializarItemsFromResources(getResources().getStringArray(R.array.lista_enlaces_tutoriales_Android),
                getResources().getStringArray(R.array.urls_enlaces_tutoriales_Android));

        //crear el fragment listado
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.add(R.id.listFragmentContenedor,new LinkListFragment());
        ft.commit();


    }


    @Override
    public void onListFragmentSelection(String tituloElegido) {
        String url = LinkData.ITEM_MAP.get(tituloElegido).getEnlaceContenido();

        webFragmentContenedor=(FrameLayout)findViewById(R.id.webFragmentContenedor);
        if(webFragmentContenedor==null){
            //dispositivo pequeño

            //Lanzamos la otra activity
            Intent i=new Intent(this,SegundaActivity.class);
            i.putExtra(SegundaActivity.EXTRA_URL,url);
            startActivity(i);

        }else{
            //dispositivo grande
            if(wvFragment==null){
                /* cambiamos esto
                            wvFragment=new WebViewFragment();
                    por esto otro
                            */
                wvFragment=WebViewFragment.newInstance(url);
            }
            if(!wvFragment.isAdded()){
                FragmentTransaction ft=fragmentManager.beginTransaction();
                ft.add(R.id.webFragmentContenedor,wvFragment);
                ft.addToBackStack(null);
                fragmentManager.executePendingTransactions();
                ft.commit();
            }
/* esto ahora no hace falta
            //muestro la url
            if (!wvFragment.getActualUrl().equals(url))
                wvFragment.mostrarUrl(url);
*/


        }



    }


}
