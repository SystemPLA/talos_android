package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.MainView;
import ru.systempla.talos_android.navigation.Screens;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> {

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    @Inject
    Router router;

    public MainActivityPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        getViewState().setToolbarTitle("Склад");
    }

    public void navigateToWarehouse(){
        getViewState().setToolbarTitle("Склад");
        router.replaceScreen(new Screens.WarehouseScreen());
    }

    public void navigateToShipments(){
        getViewState().setToolbarTitle("Отгрузки");
        router.replaceScreen(new Screens.ShipmentsScreen());
    }

    public void navigateToTools(){
        getViewState().setToolbarTitle("Инструменты");
        router.replaceScreen(new Screens.ToolsScreen());
    }
}
