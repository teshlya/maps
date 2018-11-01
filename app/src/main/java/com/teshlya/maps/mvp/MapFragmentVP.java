package com.teshlya.maps.mvp;

import com.google.android.gms.maps.model.LatLng;
import com.teshlya.maps.data.ResultAddress;
import com.teshlya.maps.utils.Geocoding;

public interface MapFragmentVP {
    interface View {
        void showAddressOnMap(ResultAddress.Address address, LatLng latLng);
        Geocoding checkSelectedGeocoding();
    }

    interface Presenter {
        void mapClick(LatLng latLng);
    }
}
