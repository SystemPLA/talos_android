package ru.systempla.talos_android.mvp.view.list;

public interface WarehouseItemView {

    int getPos();
    void setName(String name);
    void setStatus(String status);
    void setCount(long count);
}
