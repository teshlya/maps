package com.teshlya.maps.fragment;

import android.support.v4.app.Fragment;

import com.teshlya.maps.mvp.GeocodingSelected;

public abstract class BaseFragment extends Fragment implements GeocodingSelected.View {

    protected GeocodingSelected.Presenter geocodingSelectedPresenter;

    @Override
    public void atachPresenter(GeocodingSelected.Presenter presenter) {
        geocodingSelectedPresenter = presenter;
    }
}
