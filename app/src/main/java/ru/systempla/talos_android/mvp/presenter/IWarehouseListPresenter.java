package ru.systempla.talos_android.mvp.presenter;

import io.reactivex.subjects.PublishSubject;
import ru.systempla.talos_android.mvp.view.list.WarehouseItemView;

public interface IWarehouseListPresenter {
    void bind(WarehouseItemView view);
    int getCount();
    PublishSubject<WarehouseItemView> getClickSubject();
}
