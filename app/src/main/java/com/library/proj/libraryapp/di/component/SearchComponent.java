package com.library.proj.libraryapp.di.component;

import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.di.scope.ScreenScope;
import com.library.proj.libraryapp.ui.search.SearchActivity;

import dagger.Subcomponent;

/**
 * Created by Karo2 on 2017-12-31.
 */

@ScreenScope
@Subcomponent(modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}
