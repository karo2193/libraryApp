package com.library.proj.libraryapp.di.module;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Karo2 on 2017-12-31.
 */

@Module
public class MainModule {
    @ScreenScope
    @Provides
    public MainPresenter provideMainModule(UseCaseFactory useCaseFactory) {
        return new MainPresenter(useCaseFactory);
    }
}
