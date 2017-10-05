package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tecdam.fabiogsantos.sinopsefilmes.GenresFragment;
import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.task.ListGenresAsyncTask;

public class MainActivity extends AppCompatActivity implements GenresFragment.OnGenresSelectedListener {

    public static final String FRAGMENT_TAG = "fragment1";
    Fragment fragment;
    Button buttonGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGenres = (Button) findViewById(R.id.buttonGenres);
        buttonGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListGenresAsyncTask task;
                task = new ListGenresAsyncTask();
                task.execute("");
            }
        });

//        if (savedInstanceState == null) {
//          fragment = new GenresFragment();
//            getSupportFragmentManager().beginTransaction()
//                  //.add(android.R.id.content,fragment,FRAGMENT_TAG)
//                  .add(R.id.frameList,fragment,FRAGMENT_TAG)
//                  .commit();
//        } else {
//            fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
//        }
    }

    @Override
    public void onGenreSelected(Genre genre) {
        Toast.makeText(this,"Gênero do Filme: "+genre.getmName()+"("+String.valueOf(genre.getmId())+")",Toast.LENGTH_SHORT);
    }

    public Fragment getFragment() {
        return fragment;
    }

}
