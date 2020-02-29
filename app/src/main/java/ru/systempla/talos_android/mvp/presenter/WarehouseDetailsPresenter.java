package ru.systempla.talos_android.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;
import ru.systempla.talos_android.navigation.Screens;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class WarehouseDetailsPresenter extends MvpPresenter<WarehouseDetailsView> {

    @Inject
    ITalosRepo talosRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;
    private Product product;

    public WarehouseDetailsPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler, Product product) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
        this.product = product;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
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

    public void onChangeMenuPressed() {
    }

    public void onDeleteMenuPressed() {
        talosRepo.deleteProduct(product.getId()).subscribeOn(ioThreadScheduler)
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
        getViewState().setToolbarTitle("Детали по товару");
    }

}
