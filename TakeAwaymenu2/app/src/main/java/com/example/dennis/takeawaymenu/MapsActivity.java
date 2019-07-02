package com.example.dennis.takeawaymenu;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(22.38856300, 114.19572400);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Chan Kun Kee"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng sydney1 = new LatLng(22.38829900, 114.19120100);
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Lung wah hotel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));

        LatLng sydney2 = new LatLng(22.334218, 114.156525);
        mMap.addMarker(new MarkerOptions().position(sydney2).title("Kun Kee Restaurant"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));

        LatLng sydney3 = new LatLng(22.333632, 114.166723);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("Chan Kun Kun"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney3));



    }
}
