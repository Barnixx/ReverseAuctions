package pl.reverseAuctions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.reverseAuctions.model.category.Category;
import pl.reverseAuctions.model.category.CategoryDao;
import pl.reverseAuctions.model.subcategory.Subcategory;
import pl.reverseAuctions.model.subcategory.SubcategoryDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryDao categoryDao;
    private SubcategoryDao subcategoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao, SubcategoryDao subcategoryDao) {
        this.categoryDao = categoryDao;
        this.subcategoryDao = subcategoryDao;
    }

    public List<Category> getCategoryList() {
        List<Category> categories = null;
        try {
            categories = categoryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Subcategory> getSubcategoryList() {
        List<Subcategory> subcategories = null;
        try {
            subcategories = subcategoryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subcategories;
    }

    public Map<Category, List<Subcategory>> getSubcategoriesMap() {
        Map<Category, List<Subcategory>> subcategoryMap = new HashMap<>();

        List<Category> categories = getCategoryList();
        List<Subcategory> subcategories = getSubcategoryList();
        for (Category category : Objects.requireNonNull(categories)) {
            subcategoryMap.put(category, Objects.requireNonNull(subcategories)
                    .stream()
                    .filter(sub -> sub.getCategoryId().equals(category.getId()))
                    .collect(Collectors.toList()));
        }
        return subcategoryMap;
    }
}
