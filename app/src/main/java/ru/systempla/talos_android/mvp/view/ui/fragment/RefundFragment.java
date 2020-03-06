package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.systempla.talos_android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefundFragment extends Fragment {

    public RefundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refund, container, false);
    }
}
