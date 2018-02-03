package com.library.proj.libraryapp.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.library.proj.libraryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Karo2 on 2018-01-21.
 */

public class DictionaryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.dictionary_item_tv)
    TextView nameTv;

    public DictionaryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
