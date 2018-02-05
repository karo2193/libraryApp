package com.library.proj.libraryapp.ui.book;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.proj.libraryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.book_title_tv)
    TextView titleTv;
    @BindView(R.id.book_author_tv)
    TextView authorTv;
    @BindView(R.id.book_year_tv)
    TextView yearTv;
    @BindView(R.id.book_see_more_iv)
    ImageView seeMoreIv;

    public BookViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
