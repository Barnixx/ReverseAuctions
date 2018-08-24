package pl.reverseAuctions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reverseAuctions.model.category.Category;
import pl.reverseAuctions.model.subcategory.Subcategory;
import pl.reverseAuctions.service.CategoryService;

import java.util.List;
import java.util.Map;


@Controller
public class HomeController {

    private CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String homePage(Model model) {

        List<Category> categories = categoryService.getCategoryList();
        Map<Category, List<Subcategory>> subcategoryMap = categoryService.getSubcategoriesMap();

        model.addAttribute("categories", categories);
        model.addAttribute("subcategoriesMap", subcategoryMap);

        return "index";
    }
}
