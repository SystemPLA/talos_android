package ru.systempla.talos_android.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.systempla.talos_android.di.module.AppModule;
import ru.systempla.talos_android.di.module.CiceroneModule;
import ru.systempla.talos_android.di.module.RepoModule;
import ru.systempla.talos_android.mvp.presenter.CalculatorPresenter;
import ru.systempla.talos_android.mvp.presenter.MainActivityPresenter;
import ru.systempla.talos_android.mvp.presenter.ShipmentsPresenter;
import ru.systempla.talos_android.mvp.presenter.ToolsPresenter;
import ru.systempla.talos_android.mvp.presenter.WarehousePresenter;
import ru.systempla.talos_android.mvp.view.ui.MainActivity;
import ru.systempla.talos_android.mvp.view.ui.fragment.CalculatorFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.ShipmentsFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.ToolsFragment;
import ru.systempla.talos_android.mvp.view.ui.fragment.WarehouseFragment;

@Singleton
@Component(modules = {
        RepoModule.class,
        AppModule.class,
        CiceroneModule.class})

public interface AppComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(MainActivity mainActivity);
    void inject(WarehouseFragment warehouseFragment);
    void inject(WarehousePresenter warehousePresenter);
    void inject(ShipmentsFragment shipmentsFragment);
    void inject(ShipmentsPresenter shipmentsPresenter);
    void inject(ToolsFragment toolsFragment);
    void inject(ToolsPresenter toolsPresenter);
   }
