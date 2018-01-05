package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.ActivityModule;
import com.library.proj.libraryapp.di.module.MainModule;
import com.library.proj.libraryapp.di.module.RemoteDataModule;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Karo2 on 2017-12-30.
 */

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {

    MainComponent addModule(MainModule module);
    SearchComponent addModule(SearchModule module);
}
