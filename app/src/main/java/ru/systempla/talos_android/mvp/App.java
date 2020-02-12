package ru.systempla.talos_android.mvp;

import android.app.Application;

import ru.systempla.talos_android.di.AppComponent;
import ru.systempla.talos_android.di.module.AppModule;
import timber.log.Timber;

public class App extends Application {
    static private App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
