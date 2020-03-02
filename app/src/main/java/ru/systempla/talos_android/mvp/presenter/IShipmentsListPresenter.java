package ru.systempla.talos_android.mvp.presenter;

import io.reactivex.subjects.PublishSubject;
import ru.systempla.talos_android.mvp.view.list.ShipmentsItemView;

public interface IShipmentsListPresenter {
    void bind(ShipmentsItemView view);
    int getCount();
    PublishSubject<ShipmentsItemView> getClickSubject();
}
