package com.minu.hospitalhelper;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.minu.hospitalhelper.ItemListActivity;


import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.minu.hospitalhelper.content.Content;

import android.widget.Button;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Content.Item mItem;

    private ItemListActivity mItemListActivity;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    public ItemDetailFragment(ItemListActivity lia) {
        mItemListActivity = lia;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Content.ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = null;

        HospitalHelper hh = (HospitalHelper) this.getActivity().getApplication();
        if (mItem != null) {
            if (mItem.id == 1) {
                // Welcome
                rootView = inflater.inflate(R.layout.welcome, container, false);
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 2) {
                // Your visit
                rootView = inflater.inflate(R.layout.visit, container, false);
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 3) {
                // Hospital map
                if (!hh.getMapInflated()) {
                    rootView = inflater.inflate(R.layout.map, container, false);
                    hh.setMapView(rootView);
                    hh.setMapInflated(true);
                } else {
                    ViewGroup parent = (ViewGroup) hh.getMapView().getParent();
                    parent.removeAllViews();
                    rootView = hh.getMapView();
                }
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 4) {
                // Medical events
                rootView = inflater.inflate(R.layout.events, container, false);
                ExpandableListView elv = (ExpandableListView) rootView.findViewById(R.id.expandableList);
                ArrayList<String> headers = generateHeaders();
                HashMap<String, ArrayList<String>> items = generateItems();
                ExpandableListAdapter ela = new ExpandableListAdapter(this.getActivity(), headers, items);
                elv.setAdapter(ela);
               // ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 5) {
                // Medical history
                rootView = inflater.inflate(R.layout.history, container, false);
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 6) {
                // Entertainment
                if (!hh.getEntertainmentOn()) {
                    rootView = inflater.inflate(R.layout.entertainment, container, false);
                    hh.setEntertainmentView(rootView);
                    YouTubePlayerSupportFragment yf =
                            (YouTubePlayerSupportFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
                    yf.initialize("AIzaSyArm2Gl7RYa7h_olXHjM6zSPMTpzkcmY8A", new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if (!b) {
                                youTubePlayer.cueVideo("Kdgt1ZHkvnM");
                            }
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            // Something failed!
                            Toast.makeText(getActivity().getBaseContext(),
                                    "Initializing YouTube failed miserably.", Toast.LENGTH_LONG).show();
                        }
                    });
                    hh.setEntertainmentOn(true);
                } else {
                    ViewGroup parent = (ViewGroup) hh.getEntertainmentView().getParent();
                    parent.removeAllViews();
                    rootView = hh.getEntertainmentView();
                }
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 7) {
                rootView = inflater.inflate(R.layout.reservation, container, false);

                Spinner reasonSpinner = (Spinner) rootView.findViewById(R.id.reason);
                Spinner doctorSpinner = (Spinner) rootView.findViewById(R.id.doctors);

                ArrayAdapter<CharSequence> reasonAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                        R.array.reason_array, android.R.layout.simple_spinner_item);
                reasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                ArrayAdapter<CharSequence> doctorAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                        R.array.doctor_array, android.R.layout.simple_spinner_item);
                doctorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                reasonSpinner.setAdapter(reasonAdapter);
                doctorSpinner.setAdapter(doctorAdapter);

                TimePicker tp = (TimePicker) rootView.findViewById(R.id.timePicker);
                tp.setIs24HourView(true);
                Button reserve = (Button) rootView.findViewById(R.id.reserve);
                reserve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity().getBaseContext(),
                                "Reservation made!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        // Show the dummy content as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
        }*/

        return rootView;
    }

    private ArrayList<String> generateHeaders() {
        ArrayList<String> results = new ArrayList<String>();
        results.add("A reservation with doctor Doctorow in Seattle memorial the 18th of May");
        results.add("Visit on the doctor at 23.9.2013");
        results.add("Visit on the doctor at 10.6.2013");
        results.add("A visit to the pharmacy at 5.4.2013");
        results.add("A visit to the Arkham psychiatric ward 2.4.2013");
        return results;
    }

    private HashMap<String, ArrayList<String>> generateItems() {
        HashMap<String, ArrayList<String>> results = new HashMap<String, ArrayList<String>>();
        ArrayList<String> things1 = new ArrayList<String>();
        ArrayList<String> things2 = new ArrayList<String>();
        ArrayList<String> things3 = new ArrayList<String>();
        ArrayList<String> things4 = new ArrayList<String>();
        ArrayList<String> things5 = new ArrayList<String>();
        things1.add("A routine visit to doctor McAulkin at the Seattle Memorial Hospital. The patient was diagnosed with fever and ordered to rest.");
        things1.add("Visit log code: 123467asdw472347823476234");
        things2.add("A visit to doctor Hampton due to a skiing accident and a broken arm. A cast was put onto the arm after an X-ray examination.");
        things2.add("Visit log code: 12346723472347823dgdfg");
        things3.add("Patient bough some Prozac at the Illinois Pharmacy");
        things3.add("Visit log code: 12346723asdda47823476234");
        things4.add("A visit to the psychiatric ward of Arkham, Gotham City. DIagnosed with severe mental issues, ordered Prozac.");
        things4.add("Visit log code: kjhk346723472347823476234");
        things5.add("A reservation with doctor Doctorow in Seattle memorial, an allergy specialist.");
        things5.add("Reservation code: 8925367ywthws8o7");
        results.put("A reservation with doctor Doctorow in Seattle memorial the 18th of May", things5);
        results.put("Visit on the doctor at 23.9.2013", things1);
        results.put("Visit on the doctor at 10.6.2013", things2);
        results.put("A visit to the pharmacy at 5.4.2013", things3);
        results.put("A visit to the Arkham psychiatric ward 2.4.2013", things4);
        return results;
    }
}
