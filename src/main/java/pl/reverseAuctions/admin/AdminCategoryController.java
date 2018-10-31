package pl.reverseAuctions.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String getCategory(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("category", new Category());
        model.addAttribute("rootCategories", categoryService.getRootCategory());
        return "administrator/pages/tables";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());

        model.addAttribute("rootCategories", categoryService.getRootCategory());

        return "administrator/pages/categories/add-forms";
    }

    @PostMapping("/add")
    public String saveCategory(Model model, @Valid Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("rootCategories", categoryService.getRootCategory());
            return "administrator/pages/categories/add-forms";
        }
        categoryService.save(category);
        return "redirect:/admin/category/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/admin/category/";
    }
}
