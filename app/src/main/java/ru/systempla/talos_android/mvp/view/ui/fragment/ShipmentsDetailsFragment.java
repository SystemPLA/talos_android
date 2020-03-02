package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.presenter.WarehouseDetailsPresenter;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;

public class ShipmentsDetailsFragment extends MvpAppCompatFragment implements WarehouseDetailsView {

    public static ShipmentsDetailsFragment newInstance(StorageOperation storageOperation) {
        ShipmentsDetailsFragment fragment = new ShipmentsDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("storageOperation", storageOperation);
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    WarehouseDetailsPresenter presenter;

    @BindView(R.id.text_id_warehouse)
    TextView productId;

    @BindView(R.id.text_name_warehouse)
    TextView productName;

    @BindView(R.id.text_source_warehouse)
    TextView productSource;

    @BindView(R.id.text_status_warehouse)
    TextView productStatus;

    @BindView(R.id.text_count_warehouse)
    TextView productCount;

    @ProvidePresenter
    public WarehouseDetailsPresenter providePresenter() {
        WarehouseDetailsPresenter presenter = new WarehouseDetailsPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), (Product) getArguments().getSerializable("storageOperation"));
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_warehouse_item_fragment, container, false);
        ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.warehouse_item_details_menu, menu);
    }

    @Override
    public void onDestroy() {
        setHasOptionsMenu(false);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        presenter.onFragmentResume();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_item:
                presenter.onChangeMenuPressed();
                return true;
            case R.id.delete_item:
                presenter.onDeleteMenuPressed();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void setProductId(String id) {
        productId.setText(id);
    }

    @Override
    public void setProductName(String name) {
        productName.setText(name);
    }

    @Override
    public void setProductSource(String source) {
        productSource.setText(source);
    }

    @Override
    public void setProductStatus(String status) {
        productStatus.setText(status);
    }

    @Override
    public void setProductCount(String count) {
        productCount.setText(count);
    }
}