package ru.systempla.talos_android.mvp.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.presenter.MainActivityPresenter;
import ru.systempla.talos_android.mvp.presenter.ShipmentsPresenter;
import ru.systempla.talos_android.mvp.view.ShipmentsView;

public class ShipmentsFragment extends MvpAppCompatFragment implements ShipmentsView {

    public static ShipmentsFragment newInstance(){
        return new ShipmentsFragment();
    }

    @InjectPresenter
    ShipmentsPresenter presenter;

    @ProvidePresenter
    public ShipmentsPresenter providePresenter(){
        ShipmentsPresenter presenter = new ShipmentsPresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shipments_fragment, container, false);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }
}
