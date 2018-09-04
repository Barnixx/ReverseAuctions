package pl.reverseAuctions.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;

@Controller
@RequestMapping("/adminSite")
public class AdminController {

    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping()
    public String adminPage() {
        return "admin/pages/index";
    }

    @GetMapping("/getCategory")
    public String getCategory(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        return "admin/pages/tables";
    }

    @GetMapping("/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());

        model.addAttribute("rootCategories", categoryService.getRootCategory());

        return "admin/pages/categories/add-forms";
    }

    @GetMapping("/category")
    public String categoryAll() {
        return "admin/pages/categories/categoryTreeView";
    }
}
