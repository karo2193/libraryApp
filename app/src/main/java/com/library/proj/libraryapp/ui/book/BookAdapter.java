package com.library.proj.libraryapp.ui.book;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Book;
import com.library.proj.libraryapp.data.model.BookAvailability;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    private List<Book> books = new ArrayList<>();
    private PublishSubject<Book> onBookClickSubject = PublishSubject.create();

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = books.get(position);
        onBind(holder, book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private void onBind(BookViewHolder holder, Book book) {
        holder.titleTv.setText(book.getTitle());
        holder.authorTv.setText(book.getResponsibility());
        setupBookAvailability(holder, book);
        holder.seeMoreIv.setOnClickListener(v -> onBookClickSubject.onNext(book));
    }

    private void setupBookAvailability(BookViewHolder holder, Book book) {
        if (StringUtils.isBlank(book.getAvailability()))
            return;
        switch (BookAvailability.getType(book.getAvailability())) {
            case AVAILABLE:
                holder.availabilityIv.setBackgroundResource(R.drawable.ic_dot_green_24dp);
                break;
            case BORROW:
                holder.availabilityIv.setBackgroundResource(R.drawable.ic_dot_red_24dp);
                break;
            case READING_ROOM:
                holder.availabilityIv.setBackgroundResource(R.drawable.ic_dot_yellow_24dp);
                break;
            default:
                break;
        }
    }
}
