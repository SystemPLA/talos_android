package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface WarehouseEditView extends MvpView {
    void setToolbarTitle(String title);
    void setProductId(String id);
    void setProductName(String name);
    void setProductSource(String source);
    void setProductStatus(String status);
    void setProductCount(String count);
    void showLoading();
    void hideLoading();

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showMessage(String text);
}
