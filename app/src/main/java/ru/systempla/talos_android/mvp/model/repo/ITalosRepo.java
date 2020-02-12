package ru.systempla.talos_android.mvp.model.repo;

import java.util.List;

import io.reactivex.Single;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;

public interface ITalosRepo {

    Single<List<Product>> loadProducts();
    Single<List<Order>> loadOrders();
    Single<List<InfoData>> loadInfoData();

    Single<List<Product>> createProduct(Product product);
    Single<List<Order>> createOrder(Order order);
    Single<List<InfoData>> createInfoData(InfoData infoData);

    Single<List<Product>> editProduct(int id, Product product);
    Single<List<Order>> editOrder(int id, Order order);
    Single<List<InfoData>> editInfoData(int id, InfoData infoData);

    Single<List<Product>> deleteProduct(int id);
    Single<List<Order>> deleteOrder(int id);
    Single<List<InfoData>> deleteInfoData(int id);
}
