package com.tecdam.fabiogsantos.sinopsefilmes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tecdam.fabiogsantos.sinopsefilmes.model.Genres;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GenresFragment.OnGenresSelectedListener} interface
 * to handle interaction events.
 * Use the {@link GenresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenresFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_GENRES = "Genres";

    private Genres mGenres;
    private ListView mListViewGenres;

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
        void onGenreSelected(Genre genre);
    }

    public GenresFragment() {
        // Required empty public constructor
    }

    public static GenresFragment newInstance(Genres genres) {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GENRES, genres);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListViewGenres = (ListView) view.findViewById(R.id.listViewGenres);
        ArrayAdapter<Genre> adapterGenres = new ArrayAdapter<Genre>(getActivity(),android.R.layout.simple_list_item_1,mGenres.getmGenres());
        mListViewGenres.setAdapter(adapterGenres);
        mListViewGenres.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mListener != null) {
            mListener.onGenreSelected((Genre)adapterView.getSelectedItem());
        }
    }

}
