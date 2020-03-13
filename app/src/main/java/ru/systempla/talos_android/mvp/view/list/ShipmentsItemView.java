package ru.systempla.talos_android.mvp.view.list;

public interface ShipmentsItemView {

    int getPos();
    void setCustomer(String customer);
    void setDate(String date);
    void setType(String type);
    void setStatus(Boolean status);
}
