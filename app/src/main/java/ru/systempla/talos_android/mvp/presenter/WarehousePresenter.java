package ru.systempla.talos_android.mvp.presenter;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.view.WarehouseView;

@InjectViewState
public class WarehousePresenter extends MvpPresenter<WarehouseView> {

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    public WarehousePresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }
}
