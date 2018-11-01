package com.teshlya.maps.mvp;

import com.teshlya.maps.fragment.BaseFragment;
import com.teshlya.maps.fragment.GoogleMapFragment;
import com.teshlya.maps.fragment.OsmdroidMapFragment;
import com.teshlya.maps.utils.Geocoding;

public interface MainActivityVP {
    interface View {
        void addGoogleMapFragment(BaseFragment fragment);
        void addOsmdroidMapFragment(BaseFragment fragment);
        Geocoding checkSelectedGeocoding();
    }

    interface Presenter {
        void selectGoogleMapMenu();
        void selectOsmdroidMapMenu();
    }
}
