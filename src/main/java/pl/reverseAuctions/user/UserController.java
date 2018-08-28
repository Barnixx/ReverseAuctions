package pl.reverseAuctions.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.subcategory.Subcategory;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final CategoryService categoryService;
    private final UserService userService;

    public UserController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        return "signInPage";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String saveNewUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signUpPage";
        }
        userService.save(user);
        return "redirect:/";
    }

    @ModelAttribute("subcategoriesMap")
    private Map<Category, List<Subcategory>> categoryListMap() {
        return categoryService.getAllCategoriesWithSubcategories();
    }
}
