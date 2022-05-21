package com.example.scrollinfinitojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NestedScrollView nsvVista;
    RecyclerView rvUsuarios;
    ProgressBar pbCargando;

    static int contador = 0;
    static int aux = 0;
    static List<String> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nsvVista = findViewById(R.id.nsvVista);
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new GridLayoutManager(this, 1));
        pbCargando = findViewById(R.id.pbCargando);

        obtenerUsuarios(rvUsuarios, MainActivity.this);

        nsvVista.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    contador++;
                    pbCargando.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(contador < 10) {
                                obtenerUsuarios(rvUsuarios, MainActivity.this);
                            }
                        }
                    }, 3000);

                }

            }
        });
    }

    public static void obtenerUsuarios(RecyclerView rvUsuarios, Context cotext) {
        for (int i = 0 ; i < 10 ; i++) {
            aux++;
            listaUsuarios.add("Usuario con ID: "+aux);
        }

        rvUsuarios.setAdapter(new AdaptadorUsuarios(cotext, listaUsuarios));
    }
}