package ru.systempla.talos_android.mvp.presenter;

import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.view.ShipmentsView;

@InjectViewState
public class ShipmentsPresenter extends MvpPresenter<ShipmentsView> {

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    public ShipmentsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }
}
