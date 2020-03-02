package ru.systempla.talos_android.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.ShipmentsView;
import ru.systempla.talos_android.mvp.view.list.ShipmentsItemView;
import ru.systempla.talos_android.mvp.view.list.WarehouseItemView;
import ru.systempla.talos_android.mvp.view.ui.adapter.ShipmentsAdapter;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class ShipmentsPresenter extends MvpPresenter<ShipmentsView> {

    @Inject
    ITalosRepo talosRepo;

    @Inject
    Router router;

    private ListIShipmentsPresenter listShipmentsPresenter;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    public ShipmentsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;

        listShipmentsPresenter = new ListIShipmentsPresenter();

    }

    public ListIShipmentsPresenter getListShipmentsPresenter() {
        return listShipmentsPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadShipmentsData();
    }

    //wip
    public class ListIShipmentsPresenter implements IShipmentsListPresenter {

        PublishSubject<ShipmentsItemView> clickSubject = PublishSubject.create();

        List<StorageOperation> shipmentsBlocks = new ArrayList<>();

//        private List<Shipment> build() {
//            String[] customers = {"ООО Ромашка", "ООО Василек", "ООО Снежинка", "ИП Денис с района"};
//            String[] dates = {"04.02.20", "03.02.20", "02.02.20", "01.02.20"};
//            String[] types = {"Отгрузка", "Возврат", "Отгрузка", "Поступление нового товара"};
//            Boolean[] statuses = {true, false, true, false};
//            List<Shipment> abc = new ArrayList<>(customers.length);
//            for (int i = 0; i < customers.length; i++) {
//                abc.add(new Shipment(customers[i], dates[i], types[i], statuses[i]));
//            }
//            return abc;
//        }

        //конец заглушки

        @Override
        public void bind(ShipmentsItemView view) {
            view.setCustomer(shipmentsBlocks.get(view.getPos()).getCustomerName());
            view.setDate(shipmentsBlocks.get(view.getPos()).getDate());
            view.setStatus(shipmentsBlocks.get(view.getPos()).getPerformed());
            view.setType(shipmentsBlocks.get(view.getPos()).getType());
        }

        @Override
        public PublishSubject<ShipmentsItemView> getClickSubject() {
            return clickSubject;
        }


        @Override
        public int getCount() {
            return shipmentsBlocks.size();
        }


    }
    public void setTitle() {
        getViewState().setToolbarTitle("Отгрузки");
    }

    //wip
    private void loadShipmentsData() {
        getViewState().showLoading();
        Disposable disposable = talosRepo.loadStorageOperations()
                .subscribeOn(ioThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(model -> {
                    listShipmentsPresenter.shipmentsBlocks.clear();
                    listShipmentsPresenter.shipmentsBlocks.addAll(model);
                    getViewState().updateList();
                    getViewState().hideLoading();
                }, t -> {
                    getViewState().showMessage("Ошибка загрузки данных");
                    getViewState().hideLoading();
                });
    }
}
