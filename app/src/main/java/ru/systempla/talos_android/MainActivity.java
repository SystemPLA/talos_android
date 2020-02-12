package ru.systempla.talos_android;

import android.os.Bundle;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.presenter.StoragePresenter;
import ru.systempla.talos_android.mvp.view.MainView;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    StoragePresenter presenter;

    @ProvidePresenter
    public StoragePresenter providePresenter(){
        StoragePresenter presenter = new StoragePresenter();
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showResultProducts(List<Product> products) {
        Timber.d(products.toString());
    }

    @Override
    public void showResultOrders(List<Order> orders) {
        Timber.d(orders.toString());
    }

    @Override
    public void showResultInfoData(List<InfoData> infoData) {
        Timber.d(infoData.toString());
    }
}
