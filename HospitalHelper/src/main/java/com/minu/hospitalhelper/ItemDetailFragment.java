package com.minu.hospitalhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minu.hospitalhelper.content.Content;

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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
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
                rootView = inflater.inflate(R.layout.map, container, false);
                //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 4) {
                // Medical events
                rootView = inflater.inflate(R.layout.events, container, false);
                ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 5) {
                // Medical history
                rootView = inflater.inflate(R.layout.history, container, false);
                ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            } else if (mItem.id == 6) {
                // Entertainment
                rootView = inflater.inflate(R.layout.entertainment, container, false);
                ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
            }
        }

        // Show the dummy content as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.content);
        }*/

        return rootView;
    }
}
