package com.tecdam.fabiogsantos.sinopsefilmes.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.viewmodel.GenresViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GenresFragment.OnGenresSelectedListener} interface
 * to handle interaction events.
 * Use the {@link GenresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenresFragment extends Fragment {

    private static final String ARG_GENRES = "Genres";

    private GenresViewModel genresViewModel;

    private Genres mGenres;
    private ListView mListViewGenres;

    private BroadcastReceiver mChangeLanguageBroadcastReceiver;

    private OnGenresSelectedListener mListener;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnGenresSelectedListener {
        void onGenreSelected(Genres.Genre genre);
    }

    public GenresFragment() {
        // Required empty public constructor
    }

    public static GenresFragment newInstance(Genres genres) {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        //args.putSerializable(ARG_GENRES, genres);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mGenres = (Genres) getArguments().getSerializable(ARG_GENRES);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if  (view == null) {
            return;
        }

        mListViewGenres = view.findViewById(R.id.lstViewGenres);

        // Create a progress bar to display while the list loads
        ProgressBar progressBar = view.findViewById(R.id.progressBarListGenre);
        progressBar.setVisibility(View.VISIBLE);
        mListViewGenres.setEmptyView(progressBar);

        genresViewModel = ViewModelProviders.of(this).get(GenresViewModel.class);
        genresViewModel.init(getString(R.string.apikey),getString(R.string.language));

        // Carrega os dados da View por meio do WebService com LiveData
        subscribeUi_ListGenres(genresViewModel);

        //if (savedInstanceState == null) {
            // Configura os broadcasts necessários para a aplicação
            ConfigBroadCastReceiver();
        //}
    }

    private void subscribeUi_ListGenres(GenresViewModel genresViewModel) {
        // Update the list when the data changes
        genresViewModel.getGenres().observe(this, new Observer<Genres>() {
            @Override
            public void onChanged(@Nullable Genres genres) {
                if (genres != null) {
                    mListViewGenres.setAdapter(new GenresAdapter(getActivity(),R.layout.view_genre, genres.genres));
                    mListViewGenres.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //adapterView.setSelected(true);
                            //view.setSelected(true);
                            mListViewGenres.setItemChecked(i, true);
                            if (mListener != null) {
                                mListener.onGenreSelected((Genres.Genre)adapterView.getItemAtPosition(i));
                            }
                        }
                    });
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
            getActivity().registerReceiver(mChangeLanguageBroadcastReceiver, filter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGenresSelectedListener) {
            mListener = (OnGenresSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
