package com.teshlya.maps.retrofit;

import com.teshlya.maps.data.ResultAddress;

public interface OnFoundAddressCallback {
    void onFound(ResultAddress.Address address);
}
