package com.library.proj.libraryapp.ui.category;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.proj.libraryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Karo2 on 2018-01-11.
 */

public class CategoryViewHolder {

    @BindView(R.id.category_item_cl)
    ConstraintLayout itemCl;
    @BindView(R.id.category_item_cb)
    CheckBox itemCb;
    @BindView(R.id.category_item_name_tv)
    TextView nameTv;
    @BindView(R.id.category_item_arrow_iv)
    ImageView arrowIv;

    public CategoryViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
