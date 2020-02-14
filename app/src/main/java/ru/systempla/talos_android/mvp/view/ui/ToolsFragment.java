package ru.systempla.talos_android.mvp.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import moxy.MvpAppCompatFragment;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.view.ToolsView;

public class ToolsFragment extends MvpAppCompatFragment implements ToolsView {

    public static ToolsFragment newInstance(){
        return new ToolsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_fragment, container, false);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }
}
