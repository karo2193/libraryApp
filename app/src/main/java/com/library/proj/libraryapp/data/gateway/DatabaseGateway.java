package com.library.proj.libraryapp.data.gateway;

import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.model.CategoryResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Karo2 on 2018-01-14.
 */

public interface DatabaseGateway {
    Observable<List<Book>> getBooks(BookRequestData bookRequestData);

    Observable<List<CategoryResponse>> getAllCategories();
}
