package pl.reverseAuctions.category;


import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @ResponseBody
    public LinkedHashSet<Category> getAllCategory() {

        return categoryService.getRootCategory();
    }

    @GetMapping("/subcategory/{id}")
    @ResponseBody
    public LinkedHashSet<Category> getSubCategory(@PathVariable Long id) {

        return categoryService.getSubCategory(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }
}
