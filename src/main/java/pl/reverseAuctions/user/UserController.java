package pl.reverseAuctions.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.subcategory.Subcategory;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final CategoryService categoryService;

    public UserController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/signIn")
    public String signIn() {

        return "signInPage";
    }

    @ModelAttribute("subcategoriesMap")
    private Map<Category, List<Subcategory>> categoryListMap() {
        return categoryService.getAllCategoriesWithSubcategories();
    }
}
