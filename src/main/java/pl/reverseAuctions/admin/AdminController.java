package pl.reverseAuctions.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.user.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String get(Model model) {
        model.addAttribute("user", new User());
        return "/administrator/pages/login";
    }

    @GetMapping("/index")
    public String adminPage() {
        return "administrator/pages/index";
    }

}
