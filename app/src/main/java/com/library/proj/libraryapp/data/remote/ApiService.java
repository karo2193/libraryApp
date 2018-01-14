package com.library.proj.libraryapp.data.remote;

import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Karo2 on 2018-01-14.
 */

public interface ApiService {

    @POST(ApiConfig.Book.PATH)
    Observable<List<Book>> getBooks(@Body BookRequestData bookRequestData);
}
