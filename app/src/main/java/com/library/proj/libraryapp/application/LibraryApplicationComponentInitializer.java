package com.library.proj.libraryapp.application;

import android.app.Application;

import com.library.proj.libraryapp.di.component.AppComponent;
import com.library.proj.libraryapp.di.component.DaggerAppComponent;
import com.library.proj.libraryapp.di.module.AppModule;
import com.library.proj.libraryapp.di.module.RemoteDataModule;

/**
 * Created by Karo2 on 2017-12-31.
 */

public enum LibraryApplicationComponentInitializer {
    INSTANCE;

    private AppComponent appComponent;

    public void initAppComponent(Application application) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent
                    .builder()
                    .appModule(new AppModule(application))
                    .remoteDataModule(new RemoteDataModule())
                    .build();
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
