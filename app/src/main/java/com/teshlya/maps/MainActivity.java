package com.teshlya.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.teshlya.maps.fragment.BaseFragment;
import com.teshlya.maps.mvp.MainActivityVP;
import com.teshlya.maps.presenters.MainActivityPresenter;
import com.teshlya.maps.utils.Geocoding;

public class MainActivity extends AppCompatActivity implements MainActivityVP.View {

    private MainActivityPresenter presenter;
    private MenuItem itemDeveloper;
    private MenuItem itemLocationiq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        presenter.selectGoogleMapMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        itemDeveloper = menu.findItem(R.id.developer);
        itemLocationiq = menu.findItem(R.id.locationiq);
        return true;
    }

    @Override
    public void addGoogleMapFragment(BaseFragment fragment) {
        fragment.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void addOsmdroidMapFragment(BaseFragment fragment) {
        fragment.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public Geocoding checkSelectedGeocoding() {
        if (itemDeveloper.isChecked()) return Geocoding.DEVELOPER;
        if (itemLocationiq.isChecked()) return Geocoding.LOCATIONIQ;
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.google:
                presenter.selectGoogleMapMenu();
                break;
            case R.id.osmdroid:
                presenter.selectOsmdroidMapMenu();
                break;
        }
        item.setChecked(true);
        return true;
    }
}
