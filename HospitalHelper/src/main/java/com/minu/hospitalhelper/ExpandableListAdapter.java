package com.minu.hospitalhelper;

import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Miro on 12.1.2014.
 */

// Modified after http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> headers;
    private HashMap<String, ArrayList<String>> items;

    public ExpandableListAdapter(Context context, ArrayList<String> headers,
                                 HashMap<String, ArrayList<String>> items) {
        this.context = context;
        this.headers = headers;
        this.items = items;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.items.get(this.headers.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_element, null);
        }

        TextView item = (TextView) convertView
                .findViewById(R.id.elementText);

        item.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.items.get(this.headers.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_header, null);
        }

        TextView header = (TextView) convertView
                .findViewById(R.id.headerText);
        header.setTypeface(null, Typeface.BOLD);
        header.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
