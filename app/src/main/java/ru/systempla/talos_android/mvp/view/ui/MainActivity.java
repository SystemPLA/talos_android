package ru.systempla.talos_android.mvp.view.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.systempla.talos_android.R;
import ru.systempla.talos_android.mvp.App;
import ru.systempla.talos_android.mvp.presenter.MainActivityPresenter;
import ru.systempla.talos_android.mvp.view.MainView;
import ru.systempla.talos_android.navigation.Screens;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    Router router;

    @Inject
    NavigatorHolder navigatorHolder;

    Navigator navigator = new SupportAppNavigator(this, R.id.nav_host_fragment);

    @InjectPresenter
    MainActivityPresenter presenter;

    @ProvidePresenter
    public MainActivityPresenter providePresenter(){
        MainActivityPresenter presenter = new MainActivityPresenter(AndroidSchedulers.mainThread(), Schedulers.io());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(savedInstanceState == null) {
            router.replaceScreen(new Screens.WarehouseScreen());
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.navigation_warehouse:
                presenter.navigateToWarehouse();
                break;
            case R.id.navigation_shipments:
                presenter.navigateToShipments();
                break;
            case R.id.navigation_tools:
                presenter.navigateToTools();
                break;
        }
        return true;
    }

    @Override
    public void init(){
        setSupportActionBar(toolbar);
        setBottomNavigationSelectedItem("Склад");
    }

    @Override
    public void setToolbarTitle (String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setBottomNavigationSelectedItem (String item){
        switch (item) {
            case "Склад":
                bottomNavigationView.setSelectedItemId(R.id.navigation_warehouse);
                break;
            case "Отгрузки":
                bottomNavigationView.setSelectedItemId(R.id.navigation_shipments);
                break;
            case "Инструменты":
                bottomNavigationView.setSelectedItemId(R.id.navigation_tools);
                break;
        }
    }
}
