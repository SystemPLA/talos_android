package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.MainView;

@InjectViewState
public class StoragePresenter extends MvpPresenter<MainView> {

    @Inject
    ITalosRepo talosRepo;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getRepoData();
    }

    private void getRepoData() {
        talosRepo.loadProducts().subscribe(getViewState()::showResultProducts);
        talosRepo.loadOrders().subscribe(getViewState()::showResultOrders);
        talosRepo.loadInfoData().subscribe(getViewState()::showResultInfoData);
    }
}
