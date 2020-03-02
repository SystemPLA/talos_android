package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ShipmentsView extends MvpView {
    void init();
    void showLoading();
    void hideLoading();
    void updateList();
    void setToolbarTitle(String title);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMessage(String text);
}
