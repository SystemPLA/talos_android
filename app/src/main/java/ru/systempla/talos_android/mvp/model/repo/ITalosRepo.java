package ru.systempla.talos_android.mvp.model.repo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;

public interface ITalosRepo {

    Single<List<Product>> loadProducts();
    Single<List<Order>> loadOrders();
    Single<List<InfoData>> loadInfoData();
    Single<List<StorageOperation>> loadStorageOperations();

    Single<Product> loadProducts(int id);
    Single<Order> loadOrders(int id);
    Single<InfoData> loadInfoData(int id);
    Single<StorageOperation> loadStorageOperations(int id);

    Completable createProduct(Product product);
    Completable createOrder(Order order);
    Completable createInfoData(InfoData infoData);
    Completable createStorageOperation(StorageOperation storageOperation);

    Completable editProduct(int id, Product product);
    Completable editOrder(int id, Order order);
    Completable editInfoData(int id, InfoData infoData);
    Completable editStorageOperation(int id, StorageOperation storageOperation);

    Completable deleteProduct(int id);
    Completable deleteOrder(int id);
    Completable deleteInfoData(int id);
    Completable deleteStorageOperation(int id);
}
