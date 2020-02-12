package ru.systempla.talos_android.di.module;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.talos_android.mvp.model.api.IDataSource;

@SuppressWarnings({"unused", "WeakerAccess"})
@Module
public class ApiModule {

@Provides
public IDataSource api() {
    return new Retrofit.Builder()
        .baseUrl("https://35.237.102.95:8080/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDataSource.class);
    }
}
