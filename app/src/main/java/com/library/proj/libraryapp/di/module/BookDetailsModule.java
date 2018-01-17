package com.library.proj.libraryapp.di.module;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.book.BookPresenter;
import com.library.proj.libraryapp.ui.bookdetail.BookDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Karo2 on 2017-12-31.
 */

@Module
public class BookDetailsModule {
    @ScreenScope
    @Provides
    public BookDetailsPresenter provideBookDetailsModule(UseCaseFactory useCaseFactory) {
        return new BookDetailsPresenter(useCaseFactory);
    }
}
