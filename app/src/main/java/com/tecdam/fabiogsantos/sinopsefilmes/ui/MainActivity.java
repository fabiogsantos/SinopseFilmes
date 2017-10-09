package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.viewmodel.GenresViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner, LifecycleObserver {

    LifecycleRegistry lifecycleRegistrye = new LifecycleRegistry(this);
    private GenresViewModel genresViewModel;
    private ListView listGenres;
    private BroadcastReceiver mChangeLanguageBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genresViewModel = ViewModelProviders.of(this).get(GenresViewModel.class);
        genresViewModel.init(getString(R.string.apikey),getString(R.string.language));

        listGenres = findViewById(R.id.lstViewGenres);

        // Carrega os dados da View por meio do WebService com LiveData
        subscribeUi(genresViewModel);

        // Configura os broadcasts necessários para a aplicação
        ConfigBroadCastReceiver();

        // Configura o objeto que monitora a mudança de estado da activite
        getLifecycle().addObserver(this);
    }

    private void subscribeUi(GenresViewModel genresViewModel) {
        // Update the list when the data changes
        genresViewModel.getGenres().observe(this, new Observer<Genres>() {
            @Override
            public void onChanged(@Nullable Genres genres) {
                if (genres != null) {
                    listGenres.setAdapter(new GenresAdapter(getApplicationContext(),R.layout.view_genre, genres.genres));
                }
            }
        });
    }

    protected void ConfigBroadCastReceiver(){
        if(mChangeLanguageBroadcastReceiver == null) {
            mChangeLanguageBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (genresViewModel != null) {
                        genresViewModel.refresh(getString(R.string.apikey), getString(R.string.language));
                    }
                }
            };
            IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
            registerReceiver(mChangeLanguageBroadcastReceiver, filter);
        }
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistrye;
    }
}
