package com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SegundaActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt.EXTRA_URL";

    private WebViewFragment wvFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        //recojo la url que me llega desde la otra activity
        String url = getIntent().getStringExtra(EXTRA_URL);



        if(wvFragment==null){

            //lo creo

            //cambiamos esto
            //          wvFragment=new WebViewFragment();
            //por esto otro

            wvFragment=WebViewFragment.newInstance(url);

        }
        if(!wvFragment.isAdded()){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.add(R.id.webFragmentContenedor,wvFragment);
            getSupportFragmentManager().executePendingTransactions();
            ft.commit();
        }
/* Esto ahora no hace falta
        //muestro la url
        if (!wvFragment.getActualUrl().equals(url))
            wvFragment.mostrarUrl(url);
*/
    }
}
