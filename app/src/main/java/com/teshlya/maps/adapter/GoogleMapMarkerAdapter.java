package com.teshlya.maps.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.teshlya.maps.R;
import com.teshlya.maps.data.ResultAddress;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoogleMapMarkerAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private ResultAddress.Address address;

    @BindView(R.id.country)
    TextView country;

    @BindView(R.id.state)
    TextView state;

    @BindView(R.id.city)
    TextView city;

    public GoogleMapMarkerAdapter(Context context,ResultAddress.Address address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.info_window, null);
        ButterKnife.bind(this, view);
        setAddress();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void setAddress(){
        checkOnEmptyAndShow(country, address.getCountry());
        checkOnEmptyAndShow(state, address.getState());
        checkOnEmptyAndShow(city, address.getCity());
    }

    private void checkOnEmptyAndShow(TextView textView, String str){
        if (str == null || str.equals("")) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(str);
        }
    }
}
