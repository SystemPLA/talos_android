package ru.systempla.talos_android.mvp.model.api;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Order;
import ru.systempla.talos_android.mvp.model.entity.Product;

public interface IDataSource {
    @GET("api/v1/products/{id}")
    Single<Product> loadProducts(@Path("id") int id);

    @GET("api/v1/orders/{id}")
    Single<Order> loadOrders(@Path("id") int id);

    @GET("api/v1/info_data/{id}")
    Single<InfoData> loadInfoData(@Path("id") int id);

    @GET("api/v1/products")
    Single<List<Product>> loadProducts();

    @GET("api/v1/orders")
    Single<List<Order>> loadOrders();

    @GET("api/v1/info_data")
    Single<List<InfoData>> loadInfoData();

    @POST("api/v1/products")
    Completable createProduct(@Body Product product);

    @POST("api/v1/orders")
    Completable createOrder(@Body Order order);

    @POST("api/v1/info_data")
    Completable createInfoData(@Body InfoData infoData);

    @PUT("api/v1/products/{id}")
    Completable editProduct(@Path("id") int id, @Body Product product);

    @PUT("api/v1/orders/{id}")
    Completable editOrder(@Path("id") int id, @Body Order order);

    @PUT("api/v1/info_data/{id}")
    Completable editInfoData(@Path("id") int id, @Body InfoData infoData);

    @DELETE("api/v1/products/{id}")
    Completable deleteProduct(@Path("id") int id);

    @DELETE("api/v1/orders/{id}")
    Completable deleteOrder(@Path("id") int id);

    @DELETE("api/v1/info_data/{id}")
    Completable deleteInfoData(@Path("id") int id);
}
