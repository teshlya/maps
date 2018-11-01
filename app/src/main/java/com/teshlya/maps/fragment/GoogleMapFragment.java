package com.teshlya.maps.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.teshlya.maps.R;
import com.teshlya.maps.adapter.GoogleMapMarkerAdapter;
import com.teshlya.maps.data.ResultAddress;
import com.teshlya.maps.mvp.MapFragmentVP;
import com.teshlya.maps.presenters.MapFragmentPresenter;
import com.teshlya.maps.utils.Geocoding;

public class GoogleMapFragment extends BaseFragment implements OnMapReadyCallback, MapFragmentVP.View {

    private GoogleMap mMap;
    private MapFragmentPresenter mapFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);
        init();
        return view;
    }

    private void init() {
        initPresenter();
    }

    private void initPresenter() {
        mapFragmentPresenter = new MapFragmentPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeMap();
    }

    private void initializeMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addClickListenerMap();
    }

    private void addClickListenerMap() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mapFragmentPresenter.mapClick(latLng);
            }
        });
    }

    @Override
    public void showAddressOnMap(ResultAddress.Address address, LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.clear();
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        setAdapterMap(address);
        Marker marker = mMap.addMarker(markerOptions);
        marker.showInfoWindow();
    }

    @Override
    public Geocoding checkSelectedGeocoding() {
        return geocodingSelectedPresenter.check();
    }

    private void setAdapterMap(ResultAddress.Address address) {
        GoogleMapMarkerAdapter adapter = new GoogleMapMarkerAdapter(getContext(), address);
        mMap.setInfoWindowAdapter(adapter);
    }
}
