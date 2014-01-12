package com.minu.hospitalhelper;

import android.app.Application;
import android.view.View;
/**
 * Created by Miro on 12.1.2014.
 */
public class HospitalHelper extends Application {
    private boolean mapInflated;
    private View mapView;

    public HospitalHelper() {
        this.mapInflated = false;
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
}
