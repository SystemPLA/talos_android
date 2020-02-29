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
import ru.systempla.talos_android.mvp.view.WarehouseCreationView;
import ru.systempla.talos_android.mvp.view.WarehouseDetailsView;
import ru.systempla.talos_android.navigation.Screens;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@InjectViewState
public class WarehouseCreationPresenter extends MvpPresenter<WarehouseCreationView> {

    @Inject
    ITalosRepo talosRepo;

    @Inject
    Router router;

    private Scheduler mainThreadScheduler;
    private Scheduler ioThreadScheduler;

    public WarehouseCreationPresenter(Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }

    public void onFragmentResume() {
        getViewState().setToolbarTitle("Новый товар");
    }

    public void onCreatePressed(String name, String source, String status, String count){
        if (isValidData(name, source, status, count)) {
            createOnServer(name, source, status, count);
        }
    }

    private boolean isValidData(String name, String source, String status, String count){
        boolean result = true;
        if (name.equals("")|source.equals("")|status.equals("")|count.equals("")) {
            getViewState().showMessage("Все поля должны быть заполнены!");
            result = false;
        }
        try {
            Long.parseLong(count);
        } catch (NumberFormatException ignored) {
            getViewState().showMessage("В строке с количеством, указан неверный формат данных!");
            result = false;
        }
        return result;
    }

    private void createOnServer(String name, String source, String status, String count){
        getViewState().showLoading();
        talosRepo.createProduct(new Product(name, source, status, Long.parseLong(count)))
                .subscribeOn(ioThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(new CompletableObserver() {
                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onComplete() {
                                   getViewState().hideLoading();
                                   router.replaceScreen(new Screens.WarehouseScreen());
                               }

                               @Override
                               public void onError(Throwable e) {
                                   getViewState().showMessage("Ошибка загрузки данных");
                                   getViewState().hideLoading();
                               }
                           });
    }
}
