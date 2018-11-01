package com.teshlya.maps.presenters;

import com.teshlya.maps.fragment.BaseFragment;
import com.teshlya.maps.fragment.GoogleMapFragment;
import com.teshlya.maps.fragment.OsmdroidMapFragment;
import com.teshlya.maps.mvp.GeocodingSelected;
import com.teshlya.maps.mvp.MainActivityVP;
import com.teshlya.maps.utils.Geocoding;

public class MainActivityPresenter implements MainActivityVP.Presenter, GeocodingSelected.Presenter {

    private MainActivityVP.View view;

    public MainActivityPresenter(MainActivityVP.View view) {
        this.view = view;
    }

    @Override
    public void selectGoogleMapMenu() {
        view.addGoogleMapFragment(new GoogleMapFragment());
    }

    @Override
    public void selectOsmdroidMapMenu() {
        view.addOsmdroidMapFragment(new OsmdroidMapFragment());
    }

    @Override
    public Geocoding check() {
        return view.checkSelectedGeocoding();
    }
}
