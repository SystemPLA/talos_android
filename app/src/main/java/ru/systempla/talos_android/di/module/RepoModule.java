package ru.systempla.talos_android.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.systempla.talos_android.mvp.model.api.IDataSource;
import ru.systempla.talos_android.mvp.model.repo.ITalosRepo;
import ru.systempla.talos_android.mvp.model.repo.TalosRepo;

@Module(includes = {ApiModule.class})
public class RepoModule {

    @Singleton
    @Provides
    public ITalosRepo talosRepo(IDataSource api) {
        return new TalosRepo(api);
    }
}
