package ru.systempla.talos_android.mvp.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import moxy.MvpAppCompatFragment;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.view.WarehouseView;

public class WarehouseFragment extends MvpAppCompatFragment implements WarehouseView {

    public static WarehouseFragment newInstance(){
        return new WarehouseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.warehouse_fragment, container, false);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }
}