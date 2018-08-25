package pl.reverseAuctions.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.subcategory.Subcategory;

import java.util.List;
import java.util.Map;


@Controller
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        List<Category> categories = categoryService.getAll();
        Map<Category, List<Subcategory>> subcategoryMap = categoryService.getAllCategoriesWithSubcategories();

        model.addAttribute("categories", categories);
        model.addAttribute("subcategoriesMap", subcategoryMap);

        for (Map.Entry<Category, List<Subcategory>> entry : subcategoryMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
        return "home";
    }
}
