package com.minu.hospitalhelper;

import android.app.Application;
import android.view.View;
/**
 * Created by Miro on 12.1.2014.
 */
public class HospitalHelper extends Application {
    private boolean mapInflated;
    private boolean entertainmentOn;
    private boolean justStarted;
    private View mapView;
    private View entertainmentView;

    public HospitalHelper() {
        mapInflated = false;
        entertainmentOn = false;
        justStarted = true;
    }

    public void setMapInflated(boolean result) {
        mapInflated = result;
    }

    public boolean getMapInflated() {
        return mapInflated;
    }

    public View getMapView() {
        return mapView;
    }

    public void setMapView(View v) {
        mapView = v;
    }

    public boolean getEntertainmentOn() {
        return entertainmentOn;
    }

    public void setEntertainmentOn(boolean e) {
        entertainmentOn = e;
    }

    public View getEntertainmentView() {
        return entertainmentView;
    }

    public void setEntertainmentView(View v) {
        entertainmentView = v;
    }

    public boolean getJustStarted() { return justStarted; }

    public void setJustStarted(boolean b) { justStarted = b; }

    public void setUpBeforeClosing() {
        mapInflated = false;
        entertainmentOn = false;
        mapView = null;
        entertainmentView = null;
    }

}
