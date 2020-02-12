package ru.systempla.talos_android.mvp.model.repo;

import java.util.List;

import io.reactivex.Single;
import ru.systempla.talos_android.mvp.model.api.IDataSource;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;

public class TalosRepo implements ITalosRepo {

    private IDataSource api;

    public TalosRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<List<Product>> loadProducts() {
        return api.loadProducts();
    }

    @Override
    public Single<List<Order>> loadOrders() {
        return api.loadOrders();
    }

    @Override
    public Single<List<InfoData>> loadInfoData() {
        return api.loadInfoData();
    }

    @Override
    public Single<List<Product>> createProduct(Product product) {
        return api.createProduct(product);
    }

    @Override
    public Single<List<Order>> createOrder(Order order) {
        return api.createOrder(order);
    }

    @Override
    public Single<List<InfoData>> createInfoData(InfoData infoData) {
        return api.createInfoData(infoData);
    }

    @Override
    public Single<List<Product>> editProduct(int id, Product product) {
        return api.editProduct(id, product);
    }

    @Override
    public Single<List<Order>> editOrder(int id, Order order) {
        return api.editOrder(id,order);
    }

    @Override
    public Single<List<InfoData>> editInfoData(int id, InfoData infoData) {
        return api.editInfoData(id, infoData);
    }

    @Override
    public Single<List<Product>> deleteProduct(int id) {
        return api.deleteProduct(id);
    }

    @Override
    public Single<List<Order>> deleteOrder(int id) {
        return api.deleteOrder(id);
    }

    @Override
    public Single<List<InfoData>> deleteInfoData(int id) {
        return api.deleteInfoData(id);
    }
}
