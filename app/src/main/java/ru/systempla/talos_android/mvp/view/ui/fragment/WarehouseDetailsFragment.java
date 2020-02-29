package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.presenter.WarehouseDetailsPresenter;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;

public class WarehouseDetailsFragment extends MvpAppCompatFragment implements WarehouseDetailsView {

    public static WarehouseDetailsFragment newInstance(Product product) {
        WarehouseDetailsFragment fragment = new WarehouseDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("product", product);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_warehouse_item_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @ProvidePresenter
    public WarehouseDetailsPresenter providePresenter() {
        WarehouseDetailsPresenter presenter = new WarehouseDetailsPresenter(AndroidSchedulers.mainThread(), (Product) getArguments().getSerializable("product"));
        return presenter;
    }

    @Override
    public void setProductTitle(String title) {
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