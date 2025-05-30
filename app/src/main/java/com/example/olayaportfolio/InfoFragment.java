package com.example.olayaportfolio;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ExecutorService executorService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Initialize ExecutorService
        executorService = Executors.newSingleThreadExecutor();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.id_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        ImageButton facebookButton = view.findViewById(R.id.facebookbutton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookProfile();
            }
        });

        ImageButton instagramButton = view.findViewById(R.id.instabutton);
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagramProfile();
            }
        });

        return view;
    }
    private void openFacebookProfile() {
        String facebookUrl = "fb://facewebmodal/f?href=https://www.facebook.com/evvinluis.olaya.17/"; // Replace with your actual profile URL
        String facebookPageId = "Evvin Luis Olaya"; // Replace with your actual Facebook page ID

        Intent intent;
        try {
            // Check if the Facebook app is installed
            getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + facebookPageId));
        } catch (Exception e) {
            // If the Facebook app is not installed, open the profile in a web browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        }

        startActivity(intent);
    }
    private void openInstagramProfile() {
        String instagramUrl = "https://www.instagram.com/itzlightttt"; // Replace with your actual profile URL
        String instagramProfileId = "@itzlightttt"; // Replace with your actual Instagram profile ID or username

        Intent intent;
        try {
            // Check if the Instagram app is installed
            getContext().getPackageManager().getPackageInfo("com.instagram.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/" + instagramProfileId));
            intent.setPackage("com.instagram.android");
        } catch (PackageManager.NameNotFoundException e) {
            // If the Instagram app is not installed, open the profile in a web browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
        }

        startActivity(intent);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Execute the map operations in a background thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // Perform map operations
                LatLng sydney = new LatLng(14.465642, 121.330520);
                final MarkerOptions markerOptions = new MarkerOptions().position(sydney).title("Blk50 Lot18 Bella Vita Halayhaayin Pililla, Rizal");

                // Run the UI updates on the main thread
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMap.addMarker(markerOptions);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
