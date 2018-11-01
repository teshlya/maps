package com.teshlya.maps.fragment;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.teshlya.maps.adapter.OsmdroidMapMarkerAdapter;
import com.teshlya.maps.R;
import com.teshlya.maps.data.ResultAddress;
import com.teshlya.maps.mvp.MapFragmentVP;
import com.teshlya.maps.presenters.MapFragmentPresenter;
import com.teshlya.maps.utils.Geocoding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class OsmdroidMapFragment extends BaseFragment implements MapFragmentVP.View {

    private MapView map = null;
    private MapFragmentPresenter mapFragmentPresenter;
    private Marker previewMarker = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_osmdroid, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        initPresenter();
        initializeMap(view);
    }

    private void initPresenter() {
        mapFragmentPresenter = new MapFragmentPresenter(this);
    }

    private void initializeMap(View view) {
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        map = (MapView) view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        IMapController mapController = map.getController();
        mapController.setZoom(4);
        addClickListenerMap();
    }

    private void addClickListenerMap() {
        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                mapFragmentPresenter.mapClick(new LatLng(
                        p.getLatitude(),
                        p.getLongitude()));
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };
        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getContext(), mReceive);
        map.getOverlays().add(OverlayEvents);
    }

    @Override
    public void showAddressOnMap(ResultAddress.Address address, LatLng latLng) {
        removePreviewMarker();
        Marker marker = createNewMarker(latLng);
        setInfoWindow(marker, address);
        previewMarker = marker;
        map.getOverlays().add(marker);
    }

    @Override
    public Geocoding checkSelectedGeocoding() {
        return geocodingSelectedPresenter.check();
    }

    private void removePreviewMarker() {
        if (previewMarker != null) {
            previewMarker.setInfoWindow(null);
            map.getOverlays().remove(previewMarker);
        }
    }

    private Marker createNewMarker(LatLng latLng) {
        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(latLng.latitude, latLng.longitude));
        map.getController().animateTo(new GeoPoint(latLng.latitude, latLng.longitude));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        return marker;
    }

    private void setInfoWindow(Marker marker, ResultAddress.Address address) {
        OsmdroidMapMarkerAdapter infoWindow = new OsmdroidMapMarkerAdapter(R.layout.info_window, map);
        infoWindow.setAddress(address);
        marker.setInfoWindow(infoWindow);
        marker.showInfoWindow();
    }
}
