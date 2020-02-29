package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface WarehouseDetailsView extends MvpView {
    void setProductTitle(String title);
    void setProductId(String id);
    void setProductName(String name);
    void setProductSource(String source);
    void setProductStatus(String status);
    void setProductCount(String count);
}
