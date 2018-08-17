package pl.reverseAuctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reverseAuctions.model.category.Category;
import pl.reverseAuctions.model.category.CategoryDao;
import pl.reverseAuctions.model.subcategory.Subcategory;
import pl.reverseAuctions.model.subcategory.SubcategoryDao;
import pl.reverseAuctions.model.user.User;
import pl.reverseAuctions.model.user.UserDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private SubcategoryDao subcategoryDao;
    private CategoryDao categoryDao;
    private UserDao userDao;

    @Autowired
    public HomeController(SubcategoryDao subcategoryDao, CategoryDao categoryDao, UserDao userDao) {
        this.subcategoryDao = subcategoryDao;
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }


    @GetMapping("/")
    public String homePage(Model model) {


        User all = null;

        List<Category> categories = null;
        try {
            categories = categoryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Subcategory> subcategories = null;
        try {
            subcategories = subcategoryDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<Category, List<Subcategory>> subcategoryMap = new HashMap<>();
        for (Category category : categories) {
            subcategoryMap.put(category, subcategories
                    .stream()
                    .filter(sub -> sub.getCategoryId().equals(category.getId()))
                    .collect(Collectors.toList()));
        }


        model.addAttribute("categories", categories);
        model.addAttribute("subcategoriesMap", subcategoryMap);


        return "index";
    }
}
