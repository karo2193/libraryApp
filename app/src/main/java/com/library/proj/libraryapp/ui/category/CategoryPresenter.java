package com.library.proj.libraryapp.ui.category;

import com.library.proj.libraryapp.data.UseCaseFactory;
import com.library.proj.libraryapp.data.model.Category;
import com.library.proj.libraryapp.ui.base.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karo2 on 2017-12-31.
 */

public class CategoryPresenter extends Presenter<CategoryContract.View> implements CategoryContract.Presenter {
    private final UseCaseFactory useCaseFactory;

    public CategoryPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void getAllCategories() {
        List<Category> categories = new ArrayList<>();
        List<Category> subcategories = new ArrayList<>();
        subcategories.add(new Category("Podprawd 1", new ArrayList<Category>()));
        subcategories.add(new Category("Podprawdopodobieństwo 2", new ArrayList<Category>()));
        subcategories.add(new Category("Podprawd 3", new ArrayList<Category>()));
        categories.add(new Category("Prawdopodobieństwo", subcategories));
        categories.add(new Category("Kombinatoryka", subcategories));
        categories.add(new Category("Analiza", subcategories));
        categories.add(new Category("Analiza matematyczna", new ArrayList<Category>()));
        categories.add(new Category("Kryptografia", new ArrayList<Category>()));
        categories.add(new Category("Matematyka dyskretna", new ArrayList<Category>()));
        categories.add(new Category("Topologia", new ArrayList<Category>()));
        categories.add(new Category("Algebra", subcategories));
        view.processCategories(categories);
    }
}
