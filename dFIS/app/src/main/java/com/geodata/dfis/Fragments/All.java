package com.geodata.dfis.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geodata.dfis.R;
import com.geodata.dfis.ReportDamageActivity;
import com.geodata.dfis.Tools.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class All extends android.support.v4.app.Fragment implements  View.OnClickListener{
  //location
    LocationManager locationManager;
    final static int REQUEST_LOCATION = 199;
    String lati, longi;
    All context;
    GPSTracker gps;
    Geocoder geocoder;
    CardView btn_road, btn_bridge, btn_electricity, btn_sewage, btn_water, btn_tunnel, btn_transit ,btn_communication;

    String damagetype = "";
    //
    public All() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        btn_road = (CardView)view.findViewById(R.id.btn_road);
        btn_bridge = (CardView)view.findViewById(R.id.btn_bridge);
        btn_electricity = (CardView)view.findViewById(R.id.btn_electricity);
        btn_sewage = (CardView)view.findViewById(R.id.btn_sewage);
        btn_water = (CardView)view.findViewById(R.id.btn_water);
        btn_tunnel = (CardView)view.findViewById(R.id.btn_tunnel);
        btn_transit = (CardView)view.findViewById(R.id.btn_transit);
        btn_communication = (CardView)view.findViewById(R.id.btn_communication);

        btn_road.setOnClickListener(this);
        btn_bridge.setOnClickListener(this);
        btn_electricity.setOnClickListener(this);
        btn_sewage.setOnClickListener(this);
        btn_water.setOnClickListener(this);
        btn_tunnel.setOnClickListener(this);
        btn_transit.setOnClickListener(this);
        btn_communication.setOnClickListener(this);

        context = this;
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_road:
                damagetype = "Road";
                track();
                break;
            case R.id.btn_bridge:
                damagetype = "Bridge";
                track();
                break;
            case R.id.btn_electricity:
                damagetype = "Electricity";
                track();
                break;
            case R.id.btn_sewage:
                damagetype = "Sewage";
                track();
                break;
            case R.id.btn_water:
                damagetype = "Water";
                track();
                break;
            case R.id.btn_tunnel:
                damagetype = "Tunnel";
                track();
                break;
            case R.id.btn_transit:
                damagetype = "Mass Transit";
                track();
                break;
            case R.id.btn_communication:
                damagetype = "Communication";
                track();
                break;

        }
    }
    public void track(){

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(getActivity(),"Unable to Trace your location", Toast.LENGTH_SHORT).show();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
    }

    public void getLocation() {
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            gps = new GPSTracker(getContext(), getActivity());
            // Check if GPS enabled
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();


                lati = Double.toString(latitude);
                longi = Double.toString(longitude);


                String address = "";

                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    if (addresses != null && addresses.size() > 0) {
                         address = addresses.get(0).getAddressLine(0);
                    }else{
                        //Toast.makeText(getContext(), "I enter sa edit text ang address", Toast.LENGTH_SHORT).show();
                        address = "San Roque Naic, Cavite";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                /*String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

              String  location = address + " " + city + " " + state + " " + country + " " ;*/


                Intent intent = new Intent(getActivity(), ReportDamageActivity.class);
                intent.putExtra("lati", lati);
                intent.putExtra("longi", longi);
                //intent.putExtra("address", address);
                intent.putExtra("report", "1");
                intent.putExtra("damagetype", damagetype);
                startActivity(intent);



            } else {
                gps.showSettingsAlert();
            }
        }
    }
}
