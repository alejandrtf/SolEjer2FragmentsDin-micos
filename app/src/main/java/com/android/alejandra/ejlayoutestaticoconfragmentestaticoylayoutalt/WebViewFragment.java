package com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Sandra on 15/11/2016.
 */
public class WebViewFragment extends Fragment {
    public static final String URL_ARG_TUTORIAL_SELECCIONADO = "url_item_elegido_en_la_listview";
    private static final String ID_URL_GUARDADA = "id_url_guardada";
    private String actualUrl = ""; //usado para no recargar una url si está visible en ese momento

    private WebView webView;


    public WebViewFragment() {
    }

/*Metodo factoría creación instancia fragment

 */
    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        if (url != null) {

            Bundle args = new Bundle();
            args.putString(URL_ARG_TUTORIAL_SELECCIONADO, url);
            fragment.setArguments(args);
        }
        return fragment;
    }

    public String getActualUrl() {
        return actualUrl;
    }

/**Con la versión que funciona tanto en 2 activities como dentro de la misma, no usaremos este
 * método. Esto lo gestiona el propio fragment desde el onCreateView
 *
 */
    /**
     * Método que mostrará la url que se pasa
     *
     * @param url url a mostrar
     */
  /*  public void mostrarUrl(String url) {

        //actualizo la url actual
        actualUrl = url;

        //obtengo la webview
        webView = (WebView) getView().findViewById(R.id.wvWebPage);
        //activo javascript
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            //cargo la url
            webView.loadUrl(url);

        }
    }

*/
    //MÉTODOS DEL CICLO DE VIDA DEL FRAGMENT

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflo layout
        View v = inflater.inflate(R.layout.web_layout, container, false);
        //obtengo la webview
        webView = (WebView) v.findViewById(R.id.wvWebPage);
        //activo javascript
        if(webView!=null){
            webView.getSettings().setJavaScriptEnabled(true);
            //cargo la url
            webView.loadUrl(actualUrl);
        }
        return v;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //recojo los parámetros del fragment
        Bundle args=getArguments();
        if(args!=null){
            actualUrl=args.getString(URL_ARG_TUTORIAL_SELECCIONADO);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ID_URL_GUARDADA, actualUrl);

        webView.saveState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            actualUrl = savedInstanceState.getString(ID_URL_GUARDADA);

            webView.restoreState(savedInstanceState);
        }
    }
}
