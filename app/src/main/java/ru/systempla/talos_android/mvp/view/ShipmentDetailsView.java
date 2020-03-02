package ru.systempla.talos_android.mvp.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ShipmentDetailsView extends MvpView {
    void setToolbarTitle(String title);
    void setShipmentId(String id);
    void setShipmentDate(String date);
    void setShipmentType(String type);
    void setShipmentClient(String client);
    void setShipmentStairsFrameCount(String count);
    void setShipmentBadStairsFrameCount(String count);
    void setShipmentPassFrameCount(String count);
    void setShipmentBadPassFrameCount(String count);
    void setShipmentDiagonalConnectionCount(String count);
    void setShipmentBadDiagonalConnectionCount(String count);
    void setShipmentHorizontalConnectionCount(String count);
    void setShipmentBadHorizontalConnectionCount(String count);
    void setShipmentCrossbarCount(String count);
    void setShipmentBadCrossbarCount(String count);
    void setShipmentDeckCount(String count);
    void setShipmentBadDeckCount(String count);
    void setShipmentSupportsCount(String count);
    void setShipmentBadSupportsCount(String count);
    void setPerformedTo(Boolean status);
}
