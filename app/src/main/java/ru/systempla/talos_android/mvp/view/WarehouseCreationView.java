package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface WarehouseCreationView extends MvpView {
    void setToolbarTitle(String title);
    void showLoading();
    void hideLoading();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMessage(String text);
}
