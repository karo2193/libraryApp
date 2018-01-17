package com.library.proj.libraryapp.ui.category;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.library.proj.libraryapp.R;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.data.model.CategoryResponse;
import com.library.proj.libraryapp.di.component.ActivityComponent;
import com.library.proj.libraryapp.di.module.CategoryModule;
import com.library.proj.libraryapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity<CategoryContract.View, CategoryPresenter>
        implements CategoryContract.View {

    @BindView(R.id.category_lv)
    ExpandableListView categoryLv;

    private List<Category> categories = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_category;
    }

    @Override
    protected void performFieldInjection(ActivityComponent activityComponent) {
        activityComponent.addModule(new CategoryModule()).inject(this);
    }

    @Override
    protected int getFragmentContainer() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        getPresenter().getAllCategories();
    }

    @Override
    public void processCategories(List<CategoryResponse> categoryResponses) {
        categories.clear();
        for(CategoryResponse categoryResponse : categoryResponses) {
            categoryResponse.setCategorySubcategories();
            categories.add(categoryResponse.getCategory());
        }
        setupCategoriesLv();
    }

    @Override
    public void onAllCategoriesError(Throwable throwable) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.categories_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupCategoriesLv() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        setupOnCategoryClick(adapter);
        setupOnCategoryCheckBoxClick(adapter);
        categoryLv.setAdapter(adapter);
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
}
