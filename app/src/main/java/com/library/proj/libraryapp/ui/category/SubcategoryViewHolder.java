package com.library.proj.libraryapp.ui.category;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.proj.libraryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by Karo2 on 2018-01-11.
 */

public class SubcategoryViewHolder {

    @BindView(R.id.subcategory_item_cb)
    CheckBox itemCb;
    @BindView(R.id.subcategory_item_name_tv)
    TextView nameTv;

    public SubcategoryViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
