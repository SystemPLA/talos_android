package ru.systempla.talos_android.mvp.view.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import ru.systempla.talos_android.mvp.presenter.WarehouseCreationPresenter;
import ru.systempla.talos_android.mvp.presenter.WarehouseDetailsPresenter;
import ru.systempla.talos_android.mvp.presenter.WarehousePresenter;
import ru.systempla.talos_android.mvp.view.WarehouseCreationView;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;

public class WarehouseCreationFragment extends MvpAppCompatFragment implements WarehouseCreationView {

    public static WarehouseCreationFragment newInstance(){
        return new WarehouseCreationFragment();
    }

    @InjectPresenter
    WarehouseCreationPresenter presenter;

    @BindView(R.id.rl_loading)
    RelativeLayout loadingRelativeLayout;

    @BindView(R.id.text_name_warehouse)
    TextInputLayout productName;

    @BindView(R.id.text_source_warehouse)
    TextInputLayout productSource;

    @BindView(R.id.text_status_warehouse)
    TextInputLayout productStatus;

    @BindView(R.id.text_count_warehouse)
    TextInputLayout productCount;

    @OnClick(R.id.button_create_product)
    void onClickCreate() {
        presenter.onCreatePressed(productName.getEditText().getText().toString(),
                productSource.getEditText().getText().toString(),
                productStatus.getEditText().getText().toString(),
                productCount.getEditText().getText().toString());
    }

    @ProvidePresenter
    public WarehouseCreationPresenter providePresenter(){
        WarehouseCreationPresenter presenter = new WarehouseCreationPresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_warehouse_item_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        presenter.onFragmentResume();
        super.onResume();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToolbarTitle(String title) {
        ((MvpAppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void showLoading() {
        loadingRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingRelativeLayout.setVisibility(View.GONE);
    }

}