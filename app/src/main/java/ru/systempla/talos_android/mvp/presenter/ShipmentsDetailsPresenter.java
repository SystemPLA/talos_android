package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.ShipmentDetailsView;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;
import ru.systempla.talos_android.navigation.Screens;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class ShipmentsDetailsPresenter extends MvpPresenter<ShipmentDetailsView> {

    @Inject
    ITalosRepo talosRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;
    private StorageOperation storageOperation;

    public ShipmentsDetailsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler, StorageOperation storageOperation) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
        this.storageOperation = storageOperation;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    private void loadData() {
        Completable.fromAction(() ->
                {
                    getViewState().setShipmentId(String.valueOf(storageOperation.getId()));
                    getViewState().setPerformedTo(storageOperation.getPerformed());
                    getViewState().setShipmentDate(storageOperation.getDate());
                    getViewState().setShipmentClient(storageOperation.getCustomerName());
                    getViewState().setShipmentType(storageOperation.getType());
                    getViewState().setShipmentStairsFrameCount(String.valueOf(storageOperation.getStairsFrameCount()));
                    getViewState().setShipmentBadStairsFrameCount(String.valueOf(storageOperation.getStairsFrameBadCount()));
                    getViewState().setShipmentPassFrameCount(String.valueOf(storageOperation.getPassFrameCount()));
                    getViewState().setShipmentBadPassFrameCount(String.valueOf(storageOperation.getPassFrameBadCount()));
                    getViewState().setShipmentDiagonalConnectionCount(String.valueOf(storageOperation.getDiagonalConnectionCount()));
                    getViewState().setShipmentBadDiagonalConnectionCount(String.valueOf(storageOperation.getDiagonalConnectionBadCount()));
                    getViewState().setShipmentHorizontalConnectionCount(String.valueOf(storageOperation.getHorizontalConnectionCount()));
                    getViewState().setShipmentBadHorizontalConnectionCount(String.valueOf(storageOperation.getHorizontalConnectionBadCount()));
                    getViewState().setShipmentCrossbarCount(String.valueOf(storageOperation.getCrossbarCount()));
                    getViewState().setShipmentBadCrossbarCount(String.valueOf(storageOperation.getCrossbarBadCount()));
                    getViewState().setShipmentDeckCount(String.valueOf(storageOperation.getDeckCount()));
                    getViewState().setShipmentBadDeckCount(String.valueOf(storageOperation.getDeckBadCount()));
                    getViewState().setShipmentSupportsCount(String.valueOf(storageOperation.getSupportsCount()));
                    getViewState().setShipmentBadSupportsCount(String.valueOf(storageOperation.getSupportsBadCount()));
                }).subscribeOn(mainThreadScheduler).subscribe();
    }


    public void onDeleteMenuPressed() {
        talosRepo.deleteProduct(storageOperation.getId()).subscribeOn(ioThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                router.replaceScreen(new Screens.WarehouseScreen());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void onFragmentResume() {
        getViewState().setToolbarTitle("Детали по отгрузке");
    }

}
