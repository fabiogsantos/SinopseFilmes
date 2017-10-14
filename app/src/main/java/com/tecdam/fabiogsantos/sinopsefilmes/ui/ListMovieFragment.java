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
import android.widget.ListView;
import android.widget.TextView;

import com.tecdam.fabiogsantos.sinopsefilmes.R;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;
import com.tecdam.fabiogsantos.sinopsefilmes.model.Movie;
import com.tecdam.fabiogsantos.sinopsefilmes.model.PageListMovie;
import com.tecdam.fabiogsantos.sinopsefilmes.viewmodel.PageListMovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListMovieFragment.OnMoviesSelectedListener} interface
 * to handle interaction events.
 * Use the {@link ListMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMovieFragment extends Fragment {

    public static final String ARG_GENRE = "Genre";

    private Genres.Genre mGenre;

    private PageListMovieViewModel pageListMovieViewModel;
    private ListView mListMovies;
    private TextView mTextTitle;

    private BroadcastReceiver mChangeLanguageBroadcastReceiver;
    private IntentFilter mFilterLanguageBroadcastReceiver;

    private OnMoviesSelectedListener mListener;

    public static ListMovieFragment newInstance(Genres.Genre genre) {
        ListMovieFragment fragment = new ListMovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GENRE,genre);
        fragment.setArguments(args);
        return fragment;
    }

    public ListMovieFragment() {
        // Required empty public constructor
    }

    public Genres.Genre getGenre() {
        return mGenre;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGenre = (Genres.Genre) getArguments().getSerializable(ARG_GENRE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if  (view == null) {
            return;
        }

        mListMovies = view.findViewById(R.id.lstViewMovies);
        mTextTitle  = view.findViewById(R.id.textTitleListMovies);

        pageListMovieViewModel = ViewModelProviders.of(this).get(PageListMovieViewModel.class);
        mTextTitle.setText(getString(R.string.movie_title_list)+" - "+mGenre.name);

        pageListMovieViewModel.refresh(getString(R.string.apikey),
                getString(R.string.language),
                getString(R.string.region),
                getString(R.string.sortlistmovie),
                false,
                false,
                1,
                String.valueOf(mGenre.id));

        // Carrega os dados da View por meio do WebService com LiveData
        subscribeUi(pageListMovieViewModel);

        // Configura os broadcasts necessários para a aplicação
        ConfigBroadCastReceiver();
    }

    private void subscribeUi(PageListMovieViewModel pageListMovieViewModel) {
        // Update the list when the data changes
        pageListMovieViewModel.getPageListMovie().observe(this, new Observer<PageListMovie>() {
            @Override
            public void onChanged(@Nullable PageListMovie pageListMovie) {
                if (pageListMovie != null) {
                    mListMovies.setAdapter(new MoviesAdapter(getActivity(),R.layout.view_listmovie, pageListMovie.results));
                    mListMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //adapterView.setSelected(true);
                            //view.setSelected(true);
                            mListMovies.setItemChecked(i, true);
                            if (mListener != null) {
                                mListener.onMovieSelected((Movie) adapterView.getItemAtPosition(i));
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
                    if ((pageListMovieViewModel != null) && (mGenre != null)) {
                        pageListMovieViewModel.refresh(getString(R.string.apikey),
                                getString(R.string.language),
                                getString(R.string.region),
                                getString(R.string.sortlistmovie),
                                false,
                                false,
                                1,
                                String.valueOf(mGenre.id));
                    }
                }
            };

            if (mFilterLanguageBroadcastReceiver != null) {
                mFilterLanguageBroadcastReceiver = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
                getActivity().registerReceiver(mChangeLanguageBroadcastReceiver, mFilterLanguageBroadcastReceiver);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMoviesSelectedListener) {
            mListener = (OnMoviesSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMoviesSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
    public interface OnMoviesSelectedListener {
        void onMovieSelected(Movie movie);
    }

}
