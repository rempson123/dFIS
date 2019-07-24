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

public class Damage_Hard extends android.support.v4.app.Fragment implements View.OnClickListener {

    LocationManager locationManager;
    final static int REQUEST_LOCATION = 199;
    String lati, longi;
    GPSTracker gps;
    Geocoder geocoder;
    String damageType = "";
    CardView cardViewRoads, cardViewBridge, cardViewRailRoad, cardViewAirport,
            cardViewTunnel, cardViewDams, cardViewPark, cardViewTransmission, cardViewPublicUtility;

    public Damage_Hard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damage__hard, container, false);

        cardViewRoads = view.findViewById(R.id.cv_road_hard);
        cardViewBridge = view.findViewById(R.id.cv_bridge_hard);
        cardViewRailRoad = view.findViewById(R.id.cv_railroad_hard);
        cardViewAirport = view.findViewById(R.id.cv_airport_hard);
        cardViewTunnel = view.findViewById(R.id.cv_tunnel_hard);
        cardViewDams = view.findViewById(R.id.cv_dams_hard);
        cardViewPark = view.findViewById(R.id.cv_park_hard);
        cardViewTransmission = view.findViewById(R.id.cv_transmission_hard);
        cardViewPublicUtility = view.findViewById(R.id.cv_public_utility_hard);

        cardViewRoads.setOnClickListener(this);
        cardViewBridge.setOnClickListener(this);
        cardViewRailRoad.setOnClickListener(this);
        cardViewAirport.setOnClickListener(this);
        cardViewTunnel.setOnClickListener(this);
        cardViewDams.setOnClickListener(this);
        cardViewPark.setOnClickListener(this);
        cardViewTransmission.setOnClickListener(this);
        cardViewPublicUtility.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cv_road_hard:
                damageType = "Road";
                trackDamageHard();
                break;

            case R.id.cv_bridge_hard:
                damageType = "Bridge";
                trackDamageHard();
                break;

            case R.id.cv_railroad_hard:
                damageType = "Railroad Lines";
                trackDamageHard();
                break;

            case R.id.cv_airport_hard:
                damageType = "Airports";
                trackDamageHard();
                break;
            case R.id.cv_tunnel_hard:
                damageType = "Tunnels";
                trackDamageHard();
                break;

            case R.id.cv_dams_hard:
                damageType = "Dams";
                trackDamageHard();
                break;

            case R.id.cv_park_hard:
                damageType = "Park";
                trackDamageHard();
                break;

            case R.id.cv_transmission_hard:
                damageType = "Transmission Line";
                trackDamageHard();
                break;
            case R.id.cv_public_utility_hard:
                damageType = "Public Utility";
                trackDamageHard();
                break;
        }
    }

    public void trackDamageHard() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(getActivity(), "Unable to Trace your location", Toast.LENGTH_SHORT).show();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocationDamageHard();
        }
    }

    public void getLocationDamageHard() {
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
                    } else {
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
                intent.putExtra("damagetype", damageType);
                startActivity(intent);


            } else {
                gps.showSettingsAlert();
            }
        }
    }

}