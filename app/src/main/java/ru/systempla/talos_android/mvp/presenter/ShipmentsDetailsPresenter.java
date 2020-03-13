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

    public void onFragmentResume() {
        getViewState().setToolbarTitle("Детали по отгрузке");
    }

    public void onDeleteMenuPressed() {
        talosRepo.deleteStorageOperation(storageOperation.getId()).subscribeOn(ioThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        router.replaceScreen(new Screens.ShipmentsScreen());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void onAcceptMenuPressed() {
        if (!storageOperation.getPerformed()) {
            getViewState().showLoading();
            talosRepo.loadProducts()
                    .subscribeOn(ioThreadScheduler)
                    .observeOn(mainThreadScheduler)
                    .subscribe(list -> {
                        if (storageOperation.getType().equals("Возврат") | storageOperation.getType().equals("Поступление") |
                                storageOperation.getType().equals("возврат") | storageOperation.getType().equals("поступление")) {
                            for (int i = 0; i < list.size(); i++) {
                                switch (list.get(i).getStatus()) {
                                    case "бу": {

                                        switch (list.get(i).getName()) {
                                            case "Рама с лестницей (42х1,5)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getStairsFrameCount()));
                                                break;
                                            case "Рама проходная (42х1,5)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getPassFrameCount()));
                                                break;
                                            case "Связь диагональная, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getDiagonalConnectionCount()));
                                                break;
                                            case "Связь горизонтальная, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getHorizontalConnectionCount()));
                                                break;
                                            case "Ригель настила, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getCrossbarCount()));
                                                break;
                                            case "Настил деревянный 1х1":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getDeckCount()));
                                                break;
                                            case "Опора простая (пятки)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getSupportsCount()));
                                                break;
                                        }

                                    }
                                    break;
                                    case "брак": {

                                        switch (list.get(i).getName()) {
                                            case "Рама с лестницей (42х1,5)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getStairsFrameBadCount()));
                                                break;
                                            case "Рама проходная (42х1,5)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getPassFrameBadCount()));
                                                break;
                                            case "Связь диагональная, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getDiagonalConnectionBadCount()));
                                                break;
                                            case "Связь горизонтальная, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getHorizontalConnectionBadCount()));
                                                break;
                                            case "Ригель настила, l=3 м.":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getCrossbarBadCount()));
                                                break;
                                            case "Настил деревянный 1х1":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getDeckBadCount()));
                                                break;
                                            case "Опора простая (пятки)":
                                                balanceAdjustment(list.get(i), (list.get(i).getCount() + storageOperation.getSupportsBadCount()));
                                                break;
                                        }

                                    }
                                    break;
                                }
                            }
                            shipmentStatusChange();
                        } else {
                            boolean flag = true;
                            StringBuffer report = new StringBuffer("Нехватка: ");
                            for (int i = 0; i < list.size(); i++) {
                                switch (list.get(i).getStatus()) {
                                    case "бу": {

                                        switch (list.get(i).getName()) {
                                            case "Рама с лестницей (42х1,5)":
                                                if (list.get(i).getCount() < storageOperation.getStairsFrameCount()) {
                                                    flag = false;
                                                    report.append("Рама с лестницей (42х1,5) " + Math.abs(list.get(i).getCount() - storageOperation.getStairsFrameCount()) + " шт. ");
                                                }
                                                break;
                                            case "Рама проходная (42х1,5)":
                                                if (list.get(i).getCount() < storageOperation.getPassFrameCount()) {
                                                    flag = false;
                                                    report.append("Рама проходная (42х1,5) " + Math.abs(list.get(i).getCount() - storageOperation.getPassFrameCount()) + " шт. ");
                                                }
                                                break;
                                            case "Связь диагональная, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getDiagonalConnectionCount()) {
                                                    flag = false;
                                                    report.append("Связь диагональная, l=3 м. " + Math.abs(list.get(i).getCount() - storageOperation.getDiagonalConnectionCount()) + " шт. ");
                                                }
                                                break;
                                            case "Связь горизонтальная, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getHorizontalConnectionCount()) {
                                                    flag = false;
                                                    report.append("Связь горизонтальная, l=3 м. " + Math.abs(list.get(i).getCount() - storageOperation.getHorizontalConnectionCount()) + " шт. ");
                                                }
                                                break;
                                            case "Ригель настила, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getCrossbarCount()) {
                                                    flag = false;
                                                    report.append("Ригель настила, l=3 м. " + Math.abs(list.get(i).getCount() - storageOperation.getCrossbarCount()) + " шт. ");
                                                }
                                                break;
                                            case "Настил деревянный 1х1":
                                                if (list.get(i).getCount() < storageOperation.getDeckCount()) {
                                                    flag = false;
                                                    report.append("Настил деревянный 1х1 " + Math.abs(list.get(i).getCount() - storageOperation.getDeckCount()) + " шт. ");
                                                }
                                                break;
                                            case "Опора простая (пятки)":
                                                if (list.get(i).getCount() < storageOperation.getSupportsCount()) {
                                                    flag = false;
                                                    report.append("Опора простая (пятки) " + Math.abs(list.get(i).getCount() - storageOperation.getSupportsCount()) + " шт. ");
                                                }
                                                break;
                                        }

                                    }
                                    break;
                                    case "брак": {

                                        switch (list.get(i).getName()) {
                                            case "Рама с лестницей (42х1,5)":
                                                if (list.get(i).getCount() < storageOperation.getStairsFrameBadCount()) {
                                                    flag = false;
                                                    report.append("Рама с лестницей (42х1,5) брак " + Math.abs(list.get(i).getCount() - storageOperation.getStairsFrameBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Рама проходная (42х1,5)":
                                                if (list.get(i).getCount() < storageOperation.getPassFrameBadCount()) {
                                                    flag = false;
                                                    report.append("Рама проходная (42х1,5) брак " + Math.abs(list.get(i).getCount() - storageOperation.getPassFrameBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Связь диагональная, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getDiagonalConnectionBadCount()) {
                                                    flag = false;
                                                    report.append("Связь диагональная, l=3 м. брак " + Math.abs(list.get(i).getCount() - storageOperation.getDiagonalConnectionBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Связь горизонтальная, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getHorizontalConnectionBadCount()) {
                                                    flag = false;
                                                    report.append("Связь горизонтальная, l=3 м. брак " + Math.abs(list.get(i).getCount() - storageOperation.getHorizontalConnectionBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Ригель настила, l=3 м.":
                                                if (list.get(i).getCount() < storageOperation.getCrossbarBadCount()) {
                                                    flag = false;
                                                    report.append("Ригель настила, l=3 м. брак " + Math.abs(list.get(i).getCount() - storageOperation.getCrossbarBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Настил деревянный 1х1":
                                                if (list.get(i).getCount() < storageOperation.getDeckBadCount()) {
                                                    flag = false;
                                                    report.append("Настил деревянный 1х1 брак " + Math.abs(list.get(i).getCount() - storageOperation.getDeckBadCount()) + " шт. ");
                                                }
                                                break;
                                            case "Опора простая (пятки)":
                                                if (list.get(i).getCount() < storageOperation.getSupportsBadCount()) {
                                                    flag = false;
                                                    report.append("Опора простая (пятки) брак " + Math.abs(list.get(i).getCount() - storageOperation.getSupportsBadCount()) + " шт. ");
                                                }
                                                break;
                                        }

                                    }
                                    break;
                                }
                            }

                            if (flag) {
                                for (int i = 0; i < list.size(); i++) {
                                    switch (list.get(i).getStatus()) {
                                        case "бу": {

                                            switch (list.get(i).getName()) {
                                                case "Рама с лестницей (42х1,5)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getStairsFrameCount()));
                                                    break;
                                                case "Рама проходная (42х1,5)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getPassFrameCount()));
                                                    break;
                                                case "Связь диагональная, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getDiagonalConnectionCount()));
                                                    break;
                                                case "Связь горизонтальная, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getHorizontalConnectionCount()));
                                                    break;
                                                case "Ригель настила, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getCrossbarCount()));
                                                    break;
                                                case "Настил деревянный 1х1":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getDeckCount()));
                                                    break;
                                                case "Опора простая (пятки)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getSupportsCount()));
                                                    break;
                                            }

                                        }
                                        break;
                                        case "брак": {

                                            switch (list.get(i).getName()) {
                                                case "Рама с лестницей (42х1,5)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getStairsFrameBadCount()));
                                                    break;
                                                case "Рама проходная (42х1,5)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getPassFrameBadCount()));
                                                    break;
                                                case "Связь диагональная, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getDiagonalConnectionBadCount()));
                                                    break;
                                                case "Связь горизонтальная, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getHorizontalConnectionBadCount()));
                                                    break;
                                                case "Ригель настила, l=3 м.":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getCrossbarBadCount()));
                                                    break;
                                                case "Настил деревянный 1х1":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getDeckBadCount()));
                                                    break;
                                                case "Опора простая (пятки)":
                                                    balanceAdjustment(list.get(i), (list.get(i).getCount() - storageOperation.getSupportsBadCount()));
                                                    break;
                                            }

                                        }
                                        break;
                                    }
                                }
                                shipmentStatusChange();
                            } else {
                                getViewState().hideLoading();
                                getViewState().showMessage(report.toString());
                            }
                        }

                    }, throwable -> {
                        getViewState().hideLoading();
                        getViewState().showMessage("Ошибка связи с сервером");
                    });
        } else {
            getViewState().showMessage("Вы пытаетесь принять уже принятую отгрузку");
        }
    }

    private void shipmentStatusChange(){
        talosRepo.editStorageOperation(storageOperation.getId(), new StorageOperation(storageOperation.getDate(),
                storageOperation.getCustomerName(),storageOperation.getType(), storageOperation.getStairsFrameCount(),
                storageOperation.getPassFrameCount(), storageOperation.getDiagonalConnectionCount(),
                storageOperation.getHorizontalConnectionCount(), storageOperation.getCrossbarCount(),
                storageOperation.getDeckCount(), storageOperation.getSupportsCount(),
                storageOperation.getStairsFrameBadCount(), storageOperation.getPassFrameBadCount(),
                storageOperation.getDiagonalConnectionBadCount(), storageOperation.getHorizontalConnectionBadCount(),
                storageOperation.getCrossbarBadCount(), storageOperation.getDeckBadCount(),
                storageOperation.getSupportsBadCount(), true)).subscribeOn(ioThreadScheduler).
                observeOn(mainThreadScheduler).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                router.replaceScreen(new Screens.ShipmentsScreen());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void balanceAdjustment(Product product, long newBalance){
        talosRepo.editProduct(product.getId(), new Product(product.getName(), product.getSource(), product.getStatus(), newBalance))
                .subscribeOn(ioThreadScheduler).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
