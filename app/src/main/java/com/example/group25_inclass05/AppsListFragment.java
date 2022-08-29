/**
 * Assignment #: InClass05
 * File Name: Group25_InClass05 --- AppsListFragment.java
 * Full Name: Kristin Pflug
 */

package com.example.group25_inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppsListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_CATEGORY = "ARG_PARAM_CATEGORY";

    // TODO: Rename and change types of parameters
    private String mCategory;

    public AppsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param category Parameter 1.
     * @return A new instance of fragment AppsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppsListFragment newInstance(String category) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    AppsListFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (AppsListFragmentListener) context;
    }

    ListView listView;
    ArrayList<DataServices.App> apps = new ArrayList<>();
    AppAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps_list, container, false);
        listView = view.findViewById(R.id.listView);
        getActivity().setTitle(mCategory);
        apps = DataServices.getAppsByCategory(mCategory);

        adapter = new AppAdapter(getActivity(), R.layout.app_list_item, apps);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataServices.App app = apps.get(i);
                mListener.displayAppDetails(app);

            }
        });
        return view;
    }

    class AppAdapter extends ArrayAdapter<DataServices.App> {

        public AppAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list_item, parent, false);
            }

            DataServices.App app = getItem(position);

            TextView appName = convertView.findViewById(R.id.textViewAppName);
            TextView artistName = convertView.findViewById(R.id.textViewArtistName);
            TextView releaseDate = convertView.findViewById(R.id.textViewReleaseDate);

            appName.setText(app.name);
            artistName.setText(app.artistName);
            releaseDate.setText(app.releaseDate);

            return convertView;
        }
    }

    interface AppsListFragmentListener {
        void displayAppDetails(DataServices.App appSelected);
    }
}