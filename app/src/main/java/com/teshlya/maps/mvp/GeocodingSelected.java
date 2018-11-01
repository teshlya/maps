package com.teshlya.maps.mvp;

import com.teshlya.maps.fragment.BaseFragment;
import com.teshlya.maps.utils.Geocoding;

public interface GeocodingSelected {

    interface View {
        void atachPresenter(Presenter presenter);
    }

    interface Presenter {
        Geocoding check();
    }
}
