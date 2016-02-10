package com.scott.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scott.assignment1.ConferenceContract.Presenters;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpeakersListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpeakersListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpeakersListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpeakersListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpeakersListFragment newInstance(String param1, String param2) {
        SpeakersListFragment fragment = new SpeakersListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SpeakersListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String[] testing = new  String[]{"this Mest","wordks Bloom","now Joe"} ;
        View view = inflater.inflate(R.layout.fragment_speakers_list, container, false);

        final ConferenceDbHelper dbHelper = new ConferenceDbHelper(view.getContext());

        final Cursor cursor = dbHelper.getAllPresenters();
        ArrayList<String> values = new ArrayList<String>();

        if(cursor.moveToFirst()){
            do {

                values.add(cursor.getString(cursor.getColumnIndexOrThrow(Presenters.COLUMN_NAME_NAME)));
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }


        final ListView listView = (ListView) view.findViewById(R.id.listViewSpeakersList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String presenterClicked = listView.getItemAtPosition(position).toString();

                Cursor presenterCursor = dbHelper.getPresenterByName(presenterClicked);
                presenterCursor.moveToFirst();
                 //long presenterID = presenterCursor.getInt(presenterCursor.getColumnIndexOrThrow(Presenters._ID));
                 String name = presenterCursor.getString(presenterCursor.getColumnIndexOrThrow(Presenters.COLUMN_NAME_NAME));
                 String email = presenterCursor.getString(presenterCursor.getColumnIndexOrThrow(Presenters.COLUMN_NAME_EMAIL));
                 String affiliation = presenterCursor.getString(presenterCursor.getColumnIndexOrThrow(Presenters.COLUMN_NAME_AFFILIATION));
                 String bio = presenterCursor.getString(presenterCursor.getColumnIndexOrThrow(Presenters.COLUMN_NAME_BIO));

                Intent intent = new Intent(getActivity(), PresenterDetailsActivity.class);
                intent.putExtra("presenterName", name);
                intent.putExtra("presenterEmail", email);
                intent.putExtra("presenterAffiliation", affiliation);
                intent.putExtra("presenterBio", bio);
                dbHelper.close();

                startActivity(intent);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
