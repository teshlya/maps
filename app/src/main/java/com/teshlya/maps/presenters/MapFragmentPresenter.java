package com.teshlya.maps.presenters;

import com.google.android.gms.maps.model.LatLng;
import com.teshlya.maps.retrofit.RetrofitClient;
import com.teshlya.maps.retrofit.RetrofitClientLocationig;
import com.teshlya.maps.utils.Constants;
import com.teshlya.maps.data.QueryParameters;
import com.teshlya.maps.data.ResultAddress;
import com.teshlya.maps.mvp.MapFragmentVP;
import com.teshlya.maps.retrofit.OnFoundAddressCallback;
import com.teshlya.maps.retrofit.RetrofitClientDeveloper;

public class MapFragmentPresenter implements MapFragmentVP.Presenter, OnFoundAddressCallback {

    private MapFragmentVP.View view;
    private LatLng latLng;

    public MapFragmentPresenter(MapFragmentVP.View view) {
        this.view = view;
    }

    @Override
    public void mapClick(LatLng latLng) {
        this.latLng = latLng;
        RetrofitClient retrofitClient = null;
        String key = null;
        switch (view.checkSelectedGeocoding()) {
            case DEVELOPER:
                retrofitClient = RetrofitClientDeveloper.getInstance();
                key = Constants.KEY_DEVELOPER;
                break;
            case LOCATIONIQ:
                retrofitClient = RetrofitClientLocationig.getInstance();
                key = Constants.KEY_LOCATIONIQ;
                break;
        }
        retrofitClient.getAddress(new QueryParameters(
                        key,
                        String.valueOf(latLng.latitude),
                        String.valueOf(latLng.longitude)),
                this);
    }

    @Override
    public void onFound(ResultAddress.Address address) {
        view.showAddressOnMap(address, latLng);
    }
}
