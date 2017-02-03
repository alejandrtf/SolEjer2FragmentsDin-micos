package com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt.model.LinkData;
import com.android.alejandra.ejlayoutestaticoconfragmentestaticoylayoutalt.model.LinkData.TutorialAndroid;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentSelectionListener}
 * interface.
 */
public class LinkListFragment extends ListFragment {
    private static final String TAG = "LinkListFragment";
    private static final String ID_POSICION_LISTA_ELEGIDA = "pos";  //usado para guardar elemento elegido de la lista ante giros de pantalla
    private static final int NINGUNA_POS_ELEGIDA = -1;
    //listener
    private OnListFragmentSelectionListener mListener;
    private int posSeleccionadaActual = NINGUNA_POS_ELEGIDA;  //almacena la posición elegida en la listView. Usado para posibles giros pantalla


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentSelectionListener {

        void onListFragmentSelection(String tituloTutorial);
    }


    /**
     * Método que es ejecutado cuando el usuario selecciona un elemento de la ListView del fragment
     *
     * @param l        ListView
     * @param v        View sobre la que se pulsó
     * @param position posición que ocupa la vista sobre la que se pulsó, dentro de la listView
     * @param id       id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        //almaceno el elemento pulsado para luego restaurarlo
        posSeleccionadaActual = position;
        //obtengo el titulo pulsado y
        //aviso a mi listener con el título pulsado
        mListener.onListFragmentSelection(((String) l.getItemAtPosition(position)));


    }


    /**
     * CONSTRUCTOR BÁSICO
     */
    public LinkListFragment() {
    }


    //MÉTODOS CICLO VIDA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //asigno adaptador
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, LinkData.getStringArrayTutorialesTitulo()));

        if (savedInstanceState != null) {
            // Restauro la última posición seleccionada
            posSeleccionadaActual = savedInstanceState.getInt(ID_POSICION_LISTA_ELEGIDA, NINGUNA_POS_ELEGIDA);
        }

        //eligo tipo modo selección de la listview, para que me ilumine la opción pulsada
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //muestro el elemento seleccionado en diferente color
        getListView().setSelector(R.color.colorAccentTransparent);
        //asigno la posición seleccionada
        if (posSeleccionadaActual != NINGUNA_POS_ELEGIDA)
            getListView().setItemChecked(posSeleccionadaActual, true);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentSelectionListener) {
            mListener = (OnListFragmentSelectionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debe implementar OnListFragmentSelectionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    //NO LLEVA onCreateView porque usaré la lista de texto básica que incluye como layout la clase ListFragment


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID_POSICION_LISTA_ELEGIDA, posSeleccionadaActual);
    }



}
