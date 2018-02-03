package com.library.proj.libraryapp.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.CategoryModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;
import com.library.proj.libraryapp.ui.search.SearchActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity<CategoryContract.View, CategoryPresenter>
        implements CategoryContract.View {

    public static final String ON_BACK_CATEGORIES_EXTRA = "onBackCategoriesExtras";
    public static final int ON_BACK_RESULT_CODE = 111;

    @BindView(R.id.category_toolbar)
    Toolbar toolbar;
    @BindView(R.id.category_lv)
    ExpandableListView categoryLv;

    private  ArrayList<Category> categories = new ArrayList<>();
    private CategoryAdapter adapter;

    @OnClick(R.id.category_back_iv)
    public void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.category_clear_iv)
    public void onClearClick() {
        resetCategoriesChecked();
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupCategoriesLv();
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new CategoryModule()).inject(this);
    }

    @Override
    public void setupCategoriesLv() {
        Intent intent = getIntent();
        if (intent != null) {
            categories = intent.getParcelableArrayListExtra(SearchActivity.CATEGORY_LIST_EXTRA);
            adapter = new CategoryAdapter(categories);
            handleOnClickEvents();
            categoryLv.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(ON_BACK_CATEGORIES_EXTRA, categories);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_category;
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    private void handleOnClickEvents() {
        setupOnCategoryClick(adapter);
        setupOnCategoryCheckBoxClick(adapter);
    }

    private void setupOnCategoryCheckBoxClick(CategoryAdapter adapter) {
        adapter.getOnCategoryCheckBoxClick().subscribe(category
                -> category.setChecked(!category.isChecked()));
    }

    private void setupOnCategoryClick(CategoryAdapter adapter) {
        adapter.getOnCategoryClick().subscribe(category -> {
            int clickedCategoryPosition = categories.indexOf(category);
            if (categoryLv.isGroupExpanded(clickedCategoryPosition)) {
                categoryLv.collapseGroup(clickedCategoryPosition);
            } else {
                categoryLv.expandGroup(clickedCategoryPosition);
            }
        });
    }

    private void resetCategoriesChecked() {
        for(Category category: categories) {
            category.setChecked(false);
            resetSubcategoriesChecked(category);
        }
    }

    private void resetSubcategoriesChecked(Category category) {
        for(Category subcategory: category.getSubcategories()) {
            subcategory.setChecked(false);
        }
    }
}
