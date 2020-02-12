package ru.systempla.talos_android.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.systempla.talos_android.di.module.AppModule;
import ru.systempla.talos_android.di.module.RepoModule;
import ru.systempla.talos_android.mvp.presenter.StoragePresenter;

@Singleton
@Component(modules = {RepoModule.class,
        AppModule.class})
public interface AppComponent {

    void inject(StoragePresenter storagePresenter);
}
