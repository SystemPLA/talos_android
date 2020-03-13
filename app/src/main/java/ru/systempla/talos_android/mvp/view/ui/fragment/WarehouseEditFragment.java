package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.presenter.WarehouseEditPresenter;
import ru.systempla.talos_android.mvp.view.WarehouseEditView;

public class WarehouseEditFragment extends MvpAppCompatFragment implements WarehouseEditView {

    public static WarehouseEditFragment newInstance(Product product) {
        WarehouseEditFragment fragment = new WarehouseEditFragment();
        Bundle args = new Bundle();
        args.putSerializable("product", product);
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    WarehouseEditPresenter presenter;

    @BindView(R.id.rl_loading)
    RelativeLayout loadingRelativeLayout;

    @BindView(R.id.text_id_warehouse)
    TextView productId;

    @BindView(R.id.text_name_warehouse)
    TextInputLayout productName;

    @BindView(R.id.text_source_warehouse)
    TextInputLayout productSource;

    @BindView(R.id.text_status_warehouse)
    TextInputLayout productStatus;

    @BindView(R.id.text_count_warehouse)
    TextInputLayout productCount;

    @OnClick(R.id.button_edit_product)
    void onClickCreate() {
        presenter.onEditPressed(productName.getEditText().getText().toString(),
                productSource.getEditText().getText().toString(),
                productStatus.getEditText().getText().toString(),
                productCount.getEditText().getText().toString());
    }

    @ProvidePresenter
    public WarehouseEditPresenter providePresenter() {
        WarehouseEditPresenter presenter = new WarehouseEditPresenter(AndroidSchedulers.mainThread(), Schedulers.io(), (Product) getArguments().getSerializable("product"));
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_warehouse_item_fragment, container, false);
        ButterKnife.bind(this, view);
        App.getInstance().getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        presenter.onFragmentResume();
        super.onResume();
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
        productName.getEditText().setText(name);
    }

    @Override
    public void setProductSource(String source) {
        productSource.getEditText().setText(source);
    }

    @Override
    public void setProductStatus(String status) {
        productStatus.getEditText().setText(status);
    }

    @Override
    public void setProductCount(String count) {
        productCount.getEditText().setText(count);
    }

    @Override
    public void showLoading() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
    }
}