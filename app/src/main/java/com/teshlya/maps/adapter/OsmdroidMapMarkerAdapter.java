package com.teshlya.maps.adapter;

import android.view.View;
import android.widget.TextView;

import com.teshlya.maps.R;
import com.teshlya.maps.data.ResultAddress;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OsmdroidMapMarkerAdapter extends InfoWindow {

    @BindView(R.id.country)
    TextView country;

    @BindView(R.id.state)
    TextView state;

    @BindView(R.id.city)
    TextView city;

    public OsmdroidMapMarkerAdapter(int layoutResId, MapView mapView) {
        super(layoutResId, mapView);
        ButterKnife.bind(this, mView);
    }

    public void setAddress(ResultAddress.Address address){
        checkOnEmptyAndShow(country, address.getCountry());
        checkOnEmptyAndShow(state, address.getState());
        checkOnEmptyAndShow(city, address.getCity());    }

    private void checkOnEmptyAndShow(TextView textView, String str){
        if (str == null || str.equals("")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(str);
        }
    }

    @Override
    public void onOpen(Object item) {

    }

    @Override
    public void onClose() {

    }
}
