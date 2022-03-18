package com.example.lasai.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.lasai.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class EmergencyFragment extends Fragment {
    String data, p1, p2, p3;
    String address;
    String lan,lon;
    FusedLocationProviderClient fusedLocationProviderClient;

    public EmergencyFragment() {
//empty constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ActivityCompat.requestPermissions(EmergencyFragment.this.getActivity(), new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


        View view = inflater.inflate(R.layout.fragment_emergency,
                container, false);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                data = result.getString("key");
            }
        });

        getParentFragmentManager().setFragmentResultListener("keyz", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                p1 = result.getString("key1");
                p1 = result.getString("key2");
                p1 = result.getString("key3");
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        Button button = (Button) view.findViewById(R.id.sos_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    showlocation();
                else
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);


                if (checkPermission(Manifest.permission.SEND_SMS)) {
                    SmsManager mySmsManager = SmsManager.getDefault();
                    mySmsManager.sendTextMessage(p1, null, data + " \n" + lon+"\n"+lan, null, null);
                    mySmsManager.sendTextMessage(p2, null, data + "\n"+ lon+"\n"+lan, null, null);
                    mySmsManager.sendTextMessage(p3, null, data+"\n" + lon+"\n"+lan, null, null);


                    Toast.makeText(getActivity(), "Sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;
    }

    private void showlocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    lan = "Lattitude" + addressList.get(0).getLatitude();
                    lon = "Longitude" + addressList.get(0).getLongitude();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }



    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this.getActivity(), permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

   }