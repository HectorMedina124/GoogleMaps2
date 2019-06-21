package com.example.googlemaps2;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

public class FirstMapFragment extends SupportMapFragment {


    public FirstMapFragment() {
        // Required empty public constructor
    }

    public static FirstMapFragment newInstance() {
        return new FirstMapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = super.onCreateView(inflater, container, savedInstanceState);
        return vista;
    }

}
