package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.presenter.ShipmentsPresenter;
import ru.systempla.talos_android.mvp.view.ShipmentsView;
import ru.systempla.talos_android.mvp.view.ui.adapter.ShipmentsAdapter;
import ru.systempla.talos_android.mvp.view.ui.adapter.WarehouseRVAdapter;

public class ShipmentsFragment extends MvpAppCompatFragment implements ShipmentsView {

    private ShipmentsAdapter adapter;
    private Unbinder unbinder;

    public static ShipmentsFragment newInstance(){
        return new ShipmentsFragment();
    }

    @InjectPresenter
    ShipmentsPresenter presenter;

    @BindView(R.id.shipments_recycler)
    RecyclerView recyclerView;

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
        unbinder = ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle();
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShipmentsAdapter(presenter.getListShipmentsPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }
}
