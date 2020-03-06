package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndStrategy.class)
public interface CalculatorView extends MvpView {

    void showResult(int stairsFrameCount, int passFrameCount, int diagonalConnectionCount,
                    int horizontalConnectionCount, int crossbarCount, int deckCount,
                    int supportsCount, double costPerDay, double credit);
    void showSuccess();
    void showFailure();
    void showLoading();


}
