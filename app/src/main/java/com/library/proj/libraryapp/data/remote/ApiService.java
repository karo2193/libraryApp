package com.library.proj.libraryapp.data.remote;

import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookRequestData;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.data.model.Dictionary;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Karo2 on 2018-01-14.
 */

public interface ApiService {

    @POST(ApiConfig.Book.PATH)
    Observable<List<Book>> getBooks(@Body BookRequestData bookRequestData);

    @GET(ApiConfig.Category.PATH)
    Observable<List<CategoryResponse>> getAllCategories();

    @GET(ApiConfig.Dictionary.PATH)
    Single<Dictionary> getDictionary();
}
