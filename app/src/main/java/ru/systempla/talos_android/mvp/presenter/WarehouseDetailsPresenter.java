package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class WarehouseDetailsPresenter extends MvpPresenter<WarehouseDetailsView> {

    @Inject
    ITalosRepo talosRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Product product;

    public WarehouseDetailsPresenter(Scheduler mainThreadScheduler, Product product) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.product = product;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        changeTitle();
        loadData();
    }

    private void changeTitle() {
        getViewState().setProductTitle("Детали по товару");
   }

    private void loadData() {
        Completable.fromAction(() ->
                {
                        getViewState().setProductId(String.valueOf(product.getId()));
                        getViewState().setProductName(product.getName());
                        getViewState().setProductSource(product.getSource());
                        getViewState().setProductStatus(product.getStatus());
                        getViewState().setProductCount(String.valueOf(product.getCount()));
                }).subscribeOn(mainThreadScheduler).subscribe();
    }
}
