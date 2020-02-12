package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.MainView;

@InjectViewState
public class StoragePresenter extends MvpPresenter<MainView> {

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    @Inject
    ITalosRepo talosRepo;

    public StoragePresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getRepoData();
    }

    private void getRepoData() {
    }
}
