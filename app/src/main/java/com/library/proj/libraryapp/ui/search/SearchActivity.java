package com.library.proj.libraryapp.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.SearchModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.category.CategoryActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenter>
        implements SearchContract.View {

    @BindView(R.id.search_title_et)
    EditText searchTitleEt;
    @BindView(R.id.search_author_et)
    EditText searchAuthorEt;
    @BindView(R.id.search_inventory_number_et)
    EditText searchInventoryNumberEt;
    @BindView(R.id.search_main_signature_et)
    EditText searchMainSignatureEt;
    @BindView(R.id.search_year_spn)
    Spinner searchYearSpn;
    @BindView(R.id.search_volume_et)
    EditText searchVolumeEt;
    @BindView(R.id.search_type_spn)
    Spinner searchTypeSpn;
    @BindView(R.id.search_category_btn)
    Button searchCategoryBtn;
    @BindView(R.id.search_clear_btn)
    Button searchClearBtn;
    @BindView(R.id.search_search_btn)
    Button searchSearchBtn;
    @BindViews({R.id.search_title_et, R.id.search_author_et, R.id.search_inventory_number_et,
            R.id.search_main_signature_et, R.id.search_volume_et})
    List<EditText> allEtFields;

    @OnClick(R.id.search_clear_btn)
    public void onClearClick() {
        clearAllFields();
    }

    @OnClick(R.id.search_category_btn)
    public void onCategoryClick() {
        startActivity(new Intent(this, CategoryActivity.class));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new SearchModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    private void clearAllFields() {
        clearAllEtFields();
    }

    private void clearAllEtFields() {
        for (EditText editText : allEtFields) {
            editText.getText().clear();
        }
    }
}
