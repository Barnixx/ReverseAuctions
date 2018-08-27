package pl.reverseAuctions.category;

import org.springframework.stereotype.Service;
import pl.reverseAuctions.subcategory.Subcategory;

import java.util.List;
import java.util.Map;

@Service
public interface CategoryService {
    void save(Category category);

    void delete(Long id);

    void delete(Category category);

    Category getById(Long id);

    List<Category> getAll();

    Map<Category, List<Subcategory>> getAllCategoriesWithSubcategories();
}
