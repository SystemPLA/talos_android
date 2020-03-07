package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface ToolsView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void init();

    void startCalculator();

    void startRefund();

    void startArriving();
}
