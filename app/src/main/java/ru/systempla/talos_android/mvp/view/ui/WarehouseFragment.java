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
import ru.systempla.talos_android.mvp.presenter.WarehousePresenter;
import ru.systempla.talos_android.mvp.view.WarehouseView;
import timber.log.Timber;

public class WarehouseFragment extends MvpAppCompatFragment implements WarehouseView {

    public static WarehouseFragment newInstance(){
        return new WarehouseFragment();
    }

    @InjectPresenter
    WarehousePresenter presenter;

    @ProvidePresenter
    public WarehousePresenter providePresenter(){
        WarehousePresenter presenter = new WarehousePresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.warehouse_fragment, container, false);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }
}
