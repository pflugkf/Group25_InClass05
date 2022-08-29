/**
 * Assignment #: InClass05
 * File Name: Group25_InClass05 --- AppDetailsFragment.java
 * Full Name: Kristin Pflug
 */

package com.example.group25_inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_APP = "ARG_PARAM_APP";

    // TODO: Rename and change types of parameters
    private DataServices.App appToDisplay;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param app Parameter 1.
     * @return A new instance of fragment AppDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppDetailsFragment newInstance(DataServices.App app) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_APP, app);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appToDisplay = (DataServices.App) getArguments().getSerializable(ARG_PARAM_APP);
        }
    }

    ListView listView;
    ArrayList<String> genres = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_details, container, false);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle(R.string.details_fragment_title);
        genres = appToDisplay.genres;

        TextView appName = view.findViewById(R.id.displayAppName);
        TextView artistName = view.findViewById(R.id.displayArtistName);
        TextView releaseDate = view.findViewById(R.id.displayReleaseDate);

        appName.setText(appToDisplay.name);
        artistName.setText(appToDisplay.artistName);
        releaseDate.setText(appToDisplay.releaseDate);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, genres);
        listView.setAdapter(adapter);

        return view;
    }
}