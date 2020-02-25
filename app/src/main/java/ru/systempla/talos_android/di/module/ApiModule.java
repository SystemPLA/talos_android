package ru.systempla.talos_android.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.systempla.talos_android.mvp.model.api.IDataSource;

@SuppressWarnings({"unused", "WeakerAccess"})
@Module
public class ApiModule {

//Закоменчанные элементы нужны для отладки, но не совместимы с API 19. Их нужно удалить перед сдачей проекта.

@Provides
public IDataSource api(/*@Named("clientLogging") OkHttpClient okHttpClient*/) {
    return new Retrofit.Builder()
        .baseUrl("http://35.237.102.95:8080/")
        /*.client(okHttpClient)*/
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDataSource.class);
    }

}
