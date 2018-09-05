package pl.reverseAuctions.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
