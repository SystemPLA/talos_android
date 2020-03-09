package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndStrategy.class)
public interface ArrivingAndRefundView extends MvpView {
    void showSuccess();
    void showFailure();
    void showLoading();
}
