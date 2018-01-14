package com.library.proj.libraryapp.data.remote;

import android.content.Context;

import com.library.proj.libraryapp.data.gateway.DatabaseGateway;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class DatabaseRepository implements DatabaseGateway {

    private final ApiService apiService;
    private final Context context;

    @Inject
    public DatabaseRepository(ApiService apiService, Context context) {
        this.apiService = apiService;
        this.context = context;
    }

    @Override
    public Observable<List<Book>> getBooks(BookRequestData bookRequestData) {
        return apiService
                .getBooks(bookRequestData);
    }
}
