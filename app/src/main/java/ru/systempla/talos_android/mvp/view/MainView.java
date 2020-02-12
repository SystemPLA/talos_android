package ru.systempla.talos_android.mvp.view;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void showResultProducts(List<Product> products);
    void showResultOrders(List<Order> orders);
    void showResultInfoData(List<InfoData> infoData);
}
