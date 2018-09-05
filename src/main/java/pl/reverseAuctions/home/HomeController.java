package pl.reverseAuctions.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reverseAuctions.category.CategoryService;


@Controller
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String homePage(Model model) {

        model.addAttribute("categories", categoryService.getRootCategory());
        return "home";
    }

}
