package ru.systempla.talos_android.mvp.model;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.talos_android.mvp.model.api.MyApi;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;

public class MyModel {
    private Retrofit retrofit;
    private MyApi myApi;
    private boolean sendResult;

    public MyModel() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.237.102.95:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);
    }


    public boolean sendStorageOperation(StorageOperation storageOperation) {
        sendResult = false;
        Thread th = new Thread(() -> {
            try {
                Response response = myApi.sendStorageOperation(storageOperation).execute();
                if (response.code() == 200 || response.code() == 201 || response.code() == 202 || response.code() == 204) {//коды успеха
                    sendResult = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        th.start();
        try {
            th.join();//ждем выполнения, иначе возвращается sendResult раньше времени
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}
