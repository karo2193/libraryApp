package com.library.proj.libraryapp.data.remote;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.data.model.Dictionary;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class DatabaseRepository implements DatabaseGateway {

    private final ApiService apiService;

    @Inject
    public DatabaseRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Book>> getBooks(BookRequestData bookRequestData) {
        return apiService
                .getBooks(bookRequestData);
    }

    @Override
    public Observable<List<CategoryResponse>> getAllCategories() {
        return apiService
                .getAllCategories();
    }

    @Override
    public Single<Dictionary> getDictionary() {
        return apiService
                .getDictionary();
    }
}
