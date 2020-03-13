package ru.systempla.talos_android.mvp.model.api;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;

public interface MyApi {

    @POST("api/v1/operations")
    Call<Void> sendStorageOperation(@Body StorageOperation storageOperation);

    @GET("api/v1/info_data")
    Single<List<InfoData>> loadInfoData();

    @GET("api/v1/operations")
    Call<List<StorageOperation>> loadOperations();
}
