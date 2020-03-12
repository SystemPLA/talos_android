package ru.systempla.talos_android.mvp.model;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.CheckResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.talos_android.mvp.model.api.MyApi;
import ru.systempla.talos_android.mvp.model.entity.InfoData;
import ru.systempla.talos_android.mvp.model.entity.Product;
import ru.systempla.talos_android.mvp.model.entity.StorageOperation;

import static java.lang.Thread.sleep;

public class MyModel {
    private Retrofit retrofit;
    private MyApi myApi;
    private boolean sendResult;
    private List<InfoData> infoDataList;
    List<StorageOperation> storageOperationList;

    //TODO сделать синглтон
    public MyModel() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.237.102.95:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    public List<InfoData> getInfoData() {

        @SuppressLint("CheckResult")
        Thread th = new Thread(() -> {

            myApi.loadInfoData().subscribe(infoData -> {
                        infoDataList = (infoData);
                    },
                    throwable -> {
                        infoDataList = null;
                    });
        });


        th.start();

        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return infoDataList;
    }

    public List<StorageOperation> getStorageOperations() {
        Thread th = new Thread(() -> {


            try {
                storageOperationList = myApi.loadOperations().execute().body();

            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        th.start();

        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return storageOperationList;
    }
}
