package ru.systempla.talos_android.mvp.presenter;

import ru.systempla.talos_android.mvp.view.ui.adapter.ShipmentsAdapter;

public interface ShipmentsListPresenter {
    void bindViewHolder(ShipmentsAdapter.ShipmentsViewHolder holder, int position);
    int getCount();
    void showMore();

}
