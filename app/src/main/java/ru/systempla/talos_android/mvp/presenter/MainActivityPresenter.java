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
    }

    public void navigateToWarehouse(){
        router.replaceScreen(new Screens.WarehouseScreen());
    }

    public void navigateToShipments(){
        router.replaceScreen(new Screens.ShipmentsScreen());
    }

    public void navigateToTools(){
        router.replaceScreen(new Screens.ToolsScreen());
    }
}
