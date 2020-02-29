package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ToolsView extends MvpView {
    void init();
    void setToolbarTitle(String string);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void startCalculator();
}
