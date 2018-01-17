package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.application.LibraryApplication;
import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.di.module.AppModule;
import com.library.proj.libraryapp.di.module.RemoteDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Karo2 on 2017-12-30.
 */

@Singleton
@Component(modules = {AppModule.class, RemoteDataModule.class})
public interface AppComponent {

    UseCaseFactory getUseCaseFactory();

    void inject(LibraryApplication application);
}
