package pl.reverseAuctions.category;


import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;

@RestController
@RequestMapping("/categoryRest")
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

    @GetMapping("/{id}")
    @ResponseBody
    public LinkedHashSet<Category> getSubCategory(@PathVariable Long id) {

        return categoryService.getSubCategory(id);
    }
}
