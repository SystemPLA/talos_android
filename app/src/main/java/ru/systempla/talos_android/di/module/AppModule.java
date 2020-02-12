package ru.systempla.talos_android.di.module;

import dagger.Module;
import dagger.Provides;
import ru.systempla.talos_android.mvp.App;

@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App app(){
        return app;
    }
}
