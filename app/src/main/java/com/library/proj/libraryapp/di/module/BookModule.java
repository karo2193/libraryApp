package com.library.proj.libraryapp.di.module;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.book.BookPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Karo2 on 2017-12-31.
 */

@Module
public class BookModule {
    @ScreenScope
    @Provides
    public BookPresenter provideBookModule(UseCaseFactory useCaseFactory) {
        return new BookPresenter(useCaseFactory);
    }
}
