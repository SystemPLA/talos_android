package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;



@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface CalculatorView extends MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showResult(int stairsFrameCount, int passFrameCount, int diagonalConnectionCount,
                    int horizontalConnectionCount, int crossbarCount, int deckCount,
                    int supportsCount, double costPerDay, double costPerMonth, double credit, double sellPriceNew, double sellPriceUsed);

    void showSuccess();

    void showFailure();


    void showLoading();

    void hideLoading();


}
