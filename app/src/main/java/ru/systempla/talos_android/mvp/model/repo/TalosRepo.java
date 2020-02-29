package ru.systempla.talos_android.mvp.model.repo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.systempla.talos_android.mvp.model.api.IDataSource;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;
import timber.log.Timber;

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
    public Single<Product> loadProducts(int id) {
        return api.loadProducts(id);
    }

    @Override
    public Single<Order> loadOrders(int id) {
        return api.loadOrders(id);
    }

    @Override
    public Single<InfoData> loadInfoData(int id) {
        return api.loadInfoData(id);
    }

    @Override
    public Completable createProduct(Product product) {
        return api.createProduct(product);
    }

    @Override
    public Completable createOrder(Order order) {
        return api.createOrder(order);
    }

    @Override
    public Completable createInfoData(InfoData infoData) {
        return api.createInfoData(infoData);
    }

    @Override
    public Completable editProduct(int id, Product product) {
        return api.editProduct(id, product);
    }

    @Override
    public Completable editOrder(int id, Order order) {
        return api.editOrder(id,order);
    }

    @Override
    public Completable editInfoData(int id, InfoData infoData) {
        return api.editInfoData(id, infoData);
    }

    @Override
    public Completable deleteProduct(int id) {
        return api.deleteProduct(id);
    }

    @Override
    public Completable deleteOrder(int id) {
        return api.deleteOrder(id);
    }

    @Override
    public Completable deleteInfoData(int id) {
        return api.deleteInfoData(id);
    }
}
