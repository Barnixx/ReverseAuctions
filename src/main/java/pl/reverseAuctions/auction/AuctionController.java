package pl.reverseAuctions.auction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.reverseAuctions.category.CategoryService;

@Controller
public class AuctionController {

    private final CategoryService categoryService;
    private final AuctionService auctionService;

    public AuctionController(CategoryService categoryService, AuctionService auctionService) {
        this.categoryService = categoryService;
        this.auctionService = auctionService;
    }

    @GetMapping("/auctionList")
    public String getAutions(Model model) {
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        model.addAttribute("auctionsList", auctionService.getAll());
        return "auctionList";
    }
}
